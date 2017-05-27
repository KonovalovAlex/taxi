package project.actions;

import javax.servlet.http.HttpServletRequest;

public interface Action {
    ActionResult execute(HttpServletRequest req);
}
