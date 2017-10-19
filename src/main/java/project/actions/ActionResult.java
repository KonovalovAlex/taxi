package project.actions;

import java.util.ArrayList;

import static project.constants.Constants.*;

public class ActionResult {
    private String view;
    private boolean redirection;
    private static final ArrayList<String> pagesRedirection = new ArrayList();

    public void initRedirectFields() {
        pagesRedirection.add(ERROR);
        pagesRedirection.add(ADMIN);
        pagesRedirection.add(CLIENT);
        pagesRedirection.add(DISPATCHER);
    }

    public ActionResult() {

    }

    public ActionResult(String view) {
        this(view, false);
    }

    public ActionResult(String view, boolean redirection) {
        this.view = view;
        this.redirection = redirection;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return redirection;
    }

    public void setRedirect(boolean redirection) {
        this.redirection = redirection;
    }

    public boolean redirectResult(String page) {
        return pagesRedirection.contains(page);
    }

}

