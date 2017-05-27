package project.actions.show;

import project.actions.Action;
import project.actions.ActionResult;

import javax.servlet.http.HttpServletRequest;

import static project.constants.Constants.GO_TO_THE_OFFICE;

public class GoToTheOfficePage implements Action {
    ActionResult goToTheOfficePage = new ActionResult(GO_TO_THE_OFFICE);

    @Override
    public ActionResult execute(HttpServletRequest req) {
        return goToTheOfficePage;
    }
}
