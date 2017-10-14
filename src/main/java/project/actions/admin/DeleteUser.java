package project.actions.admin;

import org.apache.log4j.Logger;
import project.actions.Action;
import project.actions.ActionResult;

import project.entity.User;
import project.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static project.constants.Constants.ERROR;
import static project.constants.Constants.ID;
import static project.constants.Constants.USER_IS_DELETED_PAGE;


public class DeleteUser implements Action {
    private static final Logger LOGGER = Logger.getLogger(DeleteUser.class.getName());
    private ActionResult userIsDeleted = new ActionResult(USER_IS_DELETED_PAGE);
    private ActionResult error = new ActionResult(ERROR, true);
    private UserService userService = new UserService();

    @Override
    public ActionResult execute(HttpServletRequest req) {
        try {
            userService.deleteUser(Integer.valueOf(req.getParameter(ID)));//number of id
        } catch (SQLException e) {
            LOGGER.error("can't delete user", e);
            return error;
        }
        return userIsDeleted;
    }
}
