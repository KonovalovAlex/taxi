package project.actions;

import project.actions.login.EnterTheOffice;
import project.actions.show.GoToTheOfficePage;
import project.constants.Constants;
import project.actions.registration.DoRegistration;
import project.actions.show.RegistrationPage;
import project.actions.show.Welcome;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory extends Constants {
    public static Map<String, Action> actionMap = new HashMap<>();

    static {
        actionMap.put(WELCOME, new Welcome());
        actionMap.put(REGISTRATION_PAGE, new RegistrationPage());
        actionMap.put(DO_REGISTRATION,new DoRegistration());
        actionMap.put(GO_TO_THE_OFFICE,new GoToTheOfficePage());
        actionMap.put(ENTER_THE_OFFICE, new EnterTheOffice());
    }
    public static Action getAction(HttpServletRequest req) {

        return actionMap.get(req.getPathInfo().substring(1));
    }
}
