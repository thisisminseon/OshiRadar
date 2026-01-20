package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;
import model.UserDao;
import model.UserDto;
import util.PasswordBcrypt;

public class UserLogin implements Command {

    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // params
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");

        System.out.println("=== LOGIN DEBUG START ===");
        System.out.println("login_id = " + loginId);
        System.out.println("password input = " + (password != null ? "[OK]" : "null"));

        // find user
        UserDao dao = new UserDao();
        UserDto user = dao.findByLoginId(loginId);

        // id not found
        if (user == null) {
            System.out.println("RESULT: USER NOT FOUND");

            request.setAttribute(
                "loginError",
                "IDまたはパスワードが正しくありません。"
            );
            request.getRequestDispatcher("/member/login.jsp")
                   .forward(request, response);
            return;
        }

        System.out.println("RESULT: USER FOUND");
        System.out.println("DB password = " + user.getPassword());

        // password format check (BCrypt)
        String dbPw = user.getPassword();
        boolean isBcrypt = (dbPw != null && dbPw.startsWith("$2"));
        System.out.println("BCrypt format = " + isBcrypt);

        if (!isBcrypt) {
            System.out.println("ERROR: INVALID PASSWORD FORMAT");

            request.setAttribute(
                "loginError",
                "IDまたはパスワードが正しくありません。"
            );
            request.getRequestDispatcher("/member/login.jsp")
                   .forward(request, response);
            return;
        }

        // password verify
        boolean match = PasswordBcrypt.checkPassword(password, dbPw);
        System.out.println("PASSWORD MATCH = " + match);

        if (!match) {
            System.out.println("ERROR: PASSWORD NOT MATCH");

            request.setAttribute(
                "loginError",
                "IDまたはパスワードが正しくありません。"
            );
            request.getRequestDispatcher("/member/login.jsp")
                   .forward(request, response);
            return;
        }

        // login success
        HttpSession session = request.getSession();
        session.setAttribute("loginUser", user);

        System.out.println("LOGIN SUCCESS");
        System.out.println("=== LOGIN DEBUG END ===");

        // redirect to index
        response.sendRedirect(request.getContextPath() + "/");
    }
}