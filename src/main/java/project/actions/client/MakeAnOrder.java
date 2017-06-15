package project.actions.client;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.entity.Order;

import javax.servlet.http.HttpServletRequest;


import static project.constants.Constants.*;

public class MakeAnOrder  implements Action {

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        Order order = new Order();
        Integer phone = (Integer) req.getSession().getAttribute(PHONE);
        Integer userId = (Integer) req.getSession().getAttribute(ID);
        order.setStreet(req.getParameter("street"));
        order.setNumberOfHouse(req.getParameter("numberOfHouse"));
        order.setNumberOfApartment(req.getParameter("numberOfApartment"));
//        order.setDate(req.getParameter("time"));
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

        return null;
    }
}

