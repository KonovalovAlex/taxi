package project.actions.client;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.OrderPostgresDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static project.constants.Constants.*;

public class CancelTheOrder implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        HttpSession session = req.getSession(false);
        int id = (int) session.getAttribute(ID);
        managerDao.beginTransaction();
        try {
            OrderPostgresDao orderPostgresDao = managerDao.getOrderDaoPostgres();
            orderPostgresDao.cancelTheOrders(id);
        } catch (ExceptionDao e) {

        } finally {
            FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        }
        return null;
    }
}
