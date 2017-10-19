package project.actions.dispatcher;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.actions.registration.DoRegistration;
import project.dao.OrderDao;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.services.OrderService;

import javax.servlet.http.HttpServletRequest;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class RejectOrder implements Action {
    private OrderService orderService = new OrderService();
    private ActionResult actionResult = new ActionResult();
    @Override
    public ActionResult execute(HttpServletRequest req) {
        String resultFromService = orderService.rejectOrder(req);
        boolean redirect = actionResult.redirectResult(resultFromService);
        return new ActionResult(resultFromService, redirect);
    }
}
