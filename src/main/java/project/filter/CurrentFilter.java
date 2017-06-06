package project.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static project.constants.Constants.CLIENT;
import static project.constants.Constants.ROLE;

public class CurrentFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        String pathAdmin = request.getPathInfo() + "/admin";
        String pathDispatcher = request.getPathInfo() + "/dispatcher";

        boolean loggedIn = session.getAttribute(ROLE).equals(CLIENT);

        if (loggedIn || pathAdmin.equals(request.getPathInfo() + "/admin") || (pathDispatcher.equals(request.getPathInfo() + "/dispatcher"))) {
            response.sendRedirect("Controller/welcome");
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}


