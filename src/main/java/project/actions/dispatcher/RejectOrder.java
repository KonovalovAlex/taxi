package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class RejectOrder implements Action {
    private static final Logger LOGGER = Logger.getLogger(DoRegistration.class.getName());

    @Override
    public ActionResult execute(HttpServletRequest req) {
        ActionResult orderRejected = new ActionResult(ORDER_REJECTED_PAGE);
        ActionResult error = new ActionResult(ERROR, true);
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
            LOGGER.error("can't reject order",e);
            return error;
        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return orderRejected;
    }
}
