package project.actions.client;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.entity.Order;
import project.entity.User;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;


import static project.constants.Constants.*;

public class MakeAnOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(MakeAnOrder.class.getName());
    private ActionResult error = new ActionResult(ERROR,true);
    private ActionResult orderCreated = new ActionResult(ORDER_CREATED_PAGE);
    private ActionResult addressOrTimeIsNotCorrect = new ActionResult(ADDRESS_OR_TIME_IS_NOT_CORRECT);

    @Override
    public ActionResult execute(HttpServletRequest req) {

        Validator validator = new Validator();
        Order order = new Order();
        boolean time = validator.checkTime(req.getParameter(TIME));
        boolean street = validator.checkAddress(req.getParameter(STREET));
        boolean house = validator.checkAddress(req.getParameter(NUMBER_OF_HOUSE));
        boolean apartment = validator.checkAddress(req.getParameter(NUMBER_OF_APARTMENT));

        if (time & street & house & apartment) {

            Integer userId = ((User) req.getSession().getAttribute(USER)).getId();
            order.setStreet(req.getParameter(STREET));
            order.setNumberOfHouse(req.getParameter(NUMBER_OF_HOUSE));
            order.setNumberOfApartment(req.getParameter(NUMBER_OF_APARTMENT));
            order.setTime(req.getParameter(TIME));
            ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();

            managerDao.beginTransaction();
            try {
                OrderDao orderDao = managerDao.getOrderPostgresDao();
                order.setId(orderDao.insertOrder(order, userId));
                managerDao.commit();
            } catch (ExceptionDao e) {
                managerDao.rollback();
                LOGGER.error("can't create order",e);
                return error;
            } finally {
                FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
            }
        } else {
            return addressOrTimeIsNotCorrect;
        }
        return orderCreated;
    }
}

