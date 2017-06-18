package project.actions.client;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.entity.Order;
import project.util.Validator;

import javax.servlet.http.HttpServletRequest;


import static project.constants.Constants.*;

public class MakeAnOrder implements Action {
    private Validator validator = new Validator();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        boolean time = validator.checkTime(req.getParameter(TIME));
        Order order = new Order();
        Integer phone = (Integer) req.getSession().getAttribute(PHONE);
        Integer userId = (Integer) req.getSession().getAttribute(ID);
        order.setStreet(req.getParameter(STREET));
        order.setNumberOfHouse(req.getParameter(NUMBER_OF_HOUSE));
        order.setNumberOfApartment(req.getParameter(NUMBER_OF_APARTMENT));
        order.setTime(req.getParameter(TIME));
        if (time) {
            managerDao.beginTransaction();
            try {
                OrderDao orderDao = managerDao.getOrderDaoPostgres();
                order.setId(orderDao.insertOrder(order, userId));
                managerDao.commit();
            } catch (ExceptionDao e) {
                managerDao.rollback();
            } finally {
                FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
            }
        } else {
            req.setAttribute("timeIsNotCorrect", "time is not correct");
        }
        return null;
    }
}

