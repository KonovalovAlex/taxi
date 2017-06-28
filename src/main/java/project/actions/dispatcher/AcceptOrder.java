package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;
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
    private static final Logger LOGGER = Logger.getLogger(AcceptOrder.class.getName());

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ActionResult error = new ActionResult(ERROR, true);
        ActionResult dispatcher = new ActionResult(DISPATCHER);
        int idOrder = Integer.parseInt(req.getParameter(ACCEPT_ORDER));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = managerDao.getOrderPostgresDao();
        managerDao.beginTransaction();
        try {
            orderDao.acceptOrder(idOrder);
            managerDao.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            managerDao.rollback();
            LOGGER.error("can't accept order",e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return dispatcher;
    }
}
