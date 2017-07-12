package project.filter;


import project.entity.User;

import javax.servlet.*;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static project.constants.Constants.*;

public class FilterRole implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
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

        if (clientRole & (path.equals(request.getContextPath() + "/admin")
                || (path.equals(request.getContextPath() + "/dispatcher")))) {
            response.sendRedirect("/Controller/welcome");

        } else if (dispatcherRole & ((path.equals(request.getContextPath() + "/admin")
                || (path.equals(request.getContextPath() + "/client"))))) {
            response.sendRedirect("/Controller/welcome");

        } else if (adminRole) {
            chain.doFilter(request, response);

        } else if ((!clientRole & !adminRole & !dispatcherRole) & (path.equals(request.getContextPath() + "/client")
                || path.equals(request.getContextPath() + "/admin")
                || path.equals(request.getContextPath() + "/dispatcher"))) {
            response.sendRedirect("/Controller/welcome");

        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}


