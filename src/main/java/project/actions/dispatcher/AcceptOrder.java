package project.actions.dispatcher;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.OrderDao;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.ExceptionDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;

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
        orderDao.acceptOrder(idOrder);
        FactoryDao.getInstance().putBackConnection(managerDao.returnConnection());

        return dispatcher;
    }
}
