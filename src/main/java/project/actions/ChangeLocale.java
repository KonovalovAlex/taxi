package project.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

import static project.constants.Constants.LOCALE;


public class ChangeLocale implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
/*
Referer: http://www.w3.org/hypertext/DataSources/Overview.html

This optional header field allows the client to specify, for the server's benefit, the address ( URI ) of the document
(or element within the document) from which the URI in the request was obtained.
 */
        String page = request.getHeader("referer");
        page = page.substring(page.indexOf("/Controller/")+12);//will "welcome."

        ActionResult currentPage = new ActionResult(page,true);
        String locale = request.getParameter(LOCALE);
        HttpSession session = request.getSession();
        session.setAttribute(LOCALE, new Locale(locale));
        return currentPage;
    }
}
