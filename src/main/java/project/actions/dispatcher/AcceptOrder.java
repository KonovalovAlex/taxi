package project.actions.dispatcher;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.ACCEPT_ORDER;
import static project.constants.Constants.DISPATCHER;
import static project.constants.Constants.ERROR;

public class AcceptOrder implements Action {
    ActionResult error = new ActionResult(ERROR, true);
    ActionResult dispatcher = new ActionResult(DISPATCHER);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        int idOrder = Integer.parseInt(req.getParameter(ACCEPT_ORDER));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = managerDao.getOrderPostgresDao();
        managerDao.beginTransaction();
        try {
            if (!orderDao.acceptOrder(idOrder));
            managerDao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            managerDao.rollback();
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return dispatcher;
    }
}
