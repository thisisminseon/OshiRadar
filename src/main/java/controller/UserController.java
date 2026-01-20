package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDao;
import service.UserLogin;
import service.UserSave;
import service.UserLogout;

@WebServlet("/users/*")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getPathInfo();
        if (uri == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String page = null;

        switch (uri) {

        // login page
        case "/login.do":
            page = "/member/login.jsp";
            break;

        // login process
        case "/loginOk.do":
            new UserLogin().doCommand(request, response);
            return;

        // join page
        case "/join.do":
            page = "/member/join.jsp";
            break;

        // join process
        case "/joinOk.do":
            new UserSave().doCommand(request, response);
            return;

        // ID duplicate check (AJAX)
        case "/idCheck.do":
            response.setContentType("text/plain; charset=UTF-8");

            String loginId = request.getParameter("login_id");
            if (loginId == null || loginId.trim().isEmpty()) {
                response.getWriter().print("OK");
                return;
            }

            int cnt = new UserDao().isIdExist(loginId);
            response.getWriter().print(cnt > 0 ? "EXISTS" : "OK");
            return;

        // logout
        case "/logout.do":
            new UserLogout().doCommand(request, response);
            return;

        // help login page
        case "/helpLogin.do":
            page = "/member/helpLogin.jsp";
            break;

        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // forward to jsp
        request.getRequestDispatcher(page).forward(request, response);
    }
}