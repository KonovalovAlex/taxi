package project.actions.dispatcher;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class RejectOrder implements Action {
    ActionResult orderRejected = new ActionResult(ORDER_REJECTED, true);
    ActionResult error = new ActionResult(ERROR, true);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        int idOrder = Integer.parseInt(req.getParameter(REJECT_ORDER));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = managerDao.getOrderPostgresDao();
        managerDao.beginTransaction();
        try {
            orderDao.rejectOrder(idOrder);
            managerDao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            managerDao.rollback();
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return orderRejected;
    }
}
