package project.actions;

import project.actions.client.CancelTheOrder;
import project.actions.client.MakeAnOrder;
import project.actions.dispatcher.AcceptOrder;
import project.actions.dispatcher.RejectOrder;
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
        actionMap.put(ERROR , new ErrorPage());
        actionMap.put(ADMIN_PAGE,new AdminPage());
        actionMap.put(DISPATCHER_PAGE,new DispatcherPage());
        actionMap.put(ACCEPT_ORDER,new AcceptOrder());
        actionMap.put(REJECT_ORDER,new RejectOrder());
        actionMap.put(MAKE_AN_ORDER,new MakeAnOrder());
        actionMap.put(CANCEL_THE_ORDER,new CancelTheOrder());
    }
    public static Action getAction(HttpServletRequest req) {

        return actionMap.get(req.getPathInfo().substring(1));
    }
}
