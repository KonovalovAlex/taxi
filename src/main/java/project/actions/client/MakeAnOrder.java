package project.actions.client;

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
    private Validator validator = new Validator();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        Order order = new Order();

        boolean time = validator.checkTime(req.getParameter(TIME));
        Integer userId = ((User) req.getSession().getAttribute(USER)).getId();
        order.setStreet(req.getParameter(STREET));
        order.setNumberOfHouse(req.getParameter(NUMBER_OF_HOUSE));
        order.setNumberOfApartment(req.getParameter(NUMBER_OF_APARTMENT));
        order.setTime(req.getParameter(TIME));
        if (time) {
            ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
            managerDao.beginTransaction();
            try {
                OrderDao orderDao = managerDao.getOrderPostgresDao();
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

