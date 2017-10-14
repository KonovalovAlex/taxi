package project.actions.login;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.DaoManager;
import project.dao.postgres.FactoryDao;
import project.dao.postgres.UserPostgresDao;
import project.entity.User;
import project.services.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

import static project.constants.Constants.*;

public class ActionLogin implements Action {
    private static final Logger LOGGER = Logger.getLogger(ActionLogin.class.getName());

    private ActionResult result;
    private LoginService loginService = new LoginService();

    public ActionResult execute(HttpServletRequest req) {
        try {
            this.result = loginService.returnRolePage(req);
        } catch (SQLException e) {
            LOGGER.error("error in action login",e);
            return result;
        }
        return result;
    }
}





