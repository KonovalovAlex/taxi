package project.actions.client;

import project.actions.Action;
import project.actions.ActionResult;
import project.dao.managerDao.ManagerDao;
import project.dao.postgres.FactoryDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static project.constants.Constants.ADDRESS;
import static project.constants.Constants.CLIENT;

public class MakeAnOrder implements Action {
    private String address;

    public String createOrder(HttpServletRequest request){
        this.address = request.getParameter(ADDRESS);
        request.getParameter(CLIENT);
        HttpSession session = request.getSession();
        session.setAttribute(ADDRESS,address);
        return address;
    }
    @Override
    public ActionResult execute(HttpServletRequest req) {
        ManagerDao managerDao = FactoryDao.getInstance().getDaoManager();

        return null;
    }
}

