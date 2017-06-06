package project.actions;

import project.actions.login.ActionLogin;
import project.actions.show.*;
import project.constants.Constants;
import project.actions.registration.DoRegistration;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory extends Constants {
    public static Map<String, Action> actionMap = new HashMap<>();

    static {
        actionMap.put(WELCOME, new Welcome());
        actionMap.put(REGISTRATION, new Registration());
        actionMap.put(DO_REGISTRATION,new DoRegistration());
        actionMap.put(ACTION_LOGIN, new ActionLogin());
        actionMap.put(LOGIN, new LoginPage());
        actionMap.put(ADMIN, new AdminPage());
        actionMap.put(ERROR , new ErrorPage());
    }
    public static Action getAction(HttpServletRequest req) {

        return actionMap.get(req.getPathInfo().substring(1));
    }
}
