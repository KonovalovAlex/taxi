package project.actions.show;

import project.actions.Action;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.WELCOME;

public class Welcome implements Action {
    @Override
    public ActionResult execute(HttpServletRequest req) {
        return new ActionResult(WELCOME);
    }
}
