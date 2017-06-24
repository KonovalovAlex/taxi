package project.actions.dispatcher;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.ACCEPT_ORDER;
import static project.constants.Constants.REJECT_ORDER;

public class RejectOrder implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) {
        int idOrder = Integer.parseInt(req.getParameter(REJECT_ORDER));
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();
        OrderDao orderDao = managerDao.getOrderPostgresDao();
        orderDao.rejectOrder(idOrder);
        FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());
        return null;
    }
}
