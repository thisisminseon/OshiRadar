package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserDao;
import model.UserDto;

@WebServlet("/users/*")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getPathInfo();
        if (uri == null) uri = "";

        String page = null;
        UserDao dao = new UserDao();
        HttpSession session = request.getSession();

        switch (uri) {

        case "/join.do":
            page = "/member/join.jsp";
            break;

        case "/joinOk.do": {
            UserDto joinDto = new UserDto();

            joinDto.setLoginId(request.getParameter("login_id"));
            joinDto.setUserName(request.getParameter("user_name"));
            joinDto.setNickname(request.getParameter("nickname"));
            joinDto.setPhone1(request.getParameter("phone_1"));
            joinDto.setPhone2(request.getParameter("phone_2"));
            joinDto.setAddress(request.getParameter("address"));
            joinDto.setPassword(request.getParameter("password"));

            String birth = request.getParameter("birth_date");
            joinDto.setBirthDate(java.sql.Date.valueOf(birth));

            String gender = request.getParameter("gender");
            joinDto.setGender(gender.charAt(0));

            UserDto savedUser = dao.userSave(joinDto);

            if (savedUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/join.do");
                return;
            }

            response.sendRedirect(request.getContextPath() + "/users/login.do");
            return;
        }

        case "/login.do":

            // GET : 로그인 페이지
            if ("GET".equalsIgnoreCase(request.getMethod())) {
                if (session.getAttribute("loginUser") != null) {
                    response.sendRedirect(request.getContextPath() + "/main");
                    return;
                }
                page = "/member/login.jsp";
                break;
            }

            // POST : 로그인 처리
            String loginId = request.getParameter("login_id");
            String password = request.getParameter("password");

            UserDto loginUser = dao.findByLoginId(loginId);

            if (loginUser == null || !loginUser.getPassword().equals(password)) {
                request.setAttribute("loginError", "아이디 또는 비밀번호가 올바르지 않습니다.");
                page = "/member/login.jsp";
                break;
            }

            session.setAttribute("loginUser", loginUser);
            response.sendRedirect(request.getContextPath() + "/main");
            return;

        case "/idCheck.do":
            response.setContentType("text/plain; charset=UTF-8");

            String checkId = request.getParameter("login_id");
            if (checkId == null || checkId.trim().isEmpty()) {
                response.getWriter().print("OK");
                return;
            }

            int cnt = dao.isIdExist(checkId);
            response.getWriter().print(cnt > 0 ? "EXISTS" : "OK");
            return;

        case "/logout.do":
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/main");
            return;
        }

        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}