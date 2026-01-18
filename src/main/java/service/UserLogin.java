package service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.Command;
import model.UserDao;
import model.UserDto;

public class UserLogin implements Command {

    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");

        UserDao dao = new UserDao();
        UserDto loginUser = dao.login(loginId, password);

        if (loginUser == null) {
            request.setAttribute("loginError", "IDまたはパスワードが正しくありません。");
            request.getRequestDispatcher("/users/login.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginUser", loginUser);

        response.sendRedirect(request.getContextPath() + "/");
    }
}