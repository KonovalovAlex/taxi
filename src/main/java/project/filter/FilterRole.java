package project.filter;


import project.entity.User;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static project.constants.Constants.*;

public class FilterRole implements Filter {
    private final ArrayList<String> guest = new ArrayList<>();
    private final ArrayList<String> dispatcher = new ArrayList<>();
    private final ArrayList<String> client = new ArrayList<>();

    public void init(FilterConfig filterConfig) throws ServletException {
        guest.add("/dispatcher");
        guest.add("/admin");
        guest.add("/client");

        dispatcher.add("/client");
        dispatcher.add("/admin");

        client.add("/dispatcher");
        client.add("/admin");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = request.getPathInfo();

        boolean clientRole = session.getAttribute(USER) != null && ((User) session.getAttribute(USER)).getRole().getName().equals(CLIENT);
        boolean dispatcherRole = session.getAttribute(USER) != null && ((User) session.getAttribute(USER)).getRole().getName().equals(DISPATCHER);
        boolean adminRole = session.getAttribute(USER) != null && ((User) session.getAttribute(USER)).getRole().getName().equals(ADMIN);

        if (!clientRole & !dispatcherRole & !adminRole & guest.contains(path)) {
            response.sendRedirect(WELCOME_PAGE);
        } else if (!clientRole & !adminRole & dispatcher.contains(path)) {
            response.sendRedirect(WELCOME_PAGE);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}