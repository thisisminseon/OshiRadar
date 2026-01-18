package service;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.UserDao;
import model.UserDto;
import util.PasswordBcrypt;

public class UserSave implements Command {

    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String loginId   = request.getParameter("login_id");
        String password  = request.getParameter("password");
        String userName  = request.getParameter("user_name");
        String nickname  = request.getParameter("nickname");
        String birth     = request.getParameter("birth_date");
        String genderStr = request.getParameter("gender");
        String phone1    = request.getParameter("phone_1");
        String phone2    = request.getParameter("phone_2");
        String prefecture = request.getParameter("prefecture");
        String address   = request.getParameter("address");
        String address2  = request.getParameter("address2");

        Date birthDate = Date.valueOf(birth);
        char gender = genderStr.charAt(0);

        String fullAddress = prefecture + " " + address;
        if (address2 != null && !address2.trim().isEmpty()) {
            fullAddress += " " + address2;
        }

        String hashedPassword = PasswordBcrypt.hashPassword(password);

        UserDto dto = new UserDto();
        dto.setLoginId(loginId);
        dto.setUserName(userName);
        dto.setBirthDate(birthDate);
        dto.setNickname(nickname);
        dto.setPassword(hashedPassword);
        dto.setPhone1(phone1);
        dto.setPhone2(phone2);
        dto.setGender(gender);
        dto.setAddress(fullAddress);

        UserDao dao = new UserDao();
        UserDto saved = dao.userSave(dto);

        if (saved == null) {
            response.sendRedirect(request.getContextPath() + "/users/join.do?error=duplicate");
            return;
        }

        response.sendRedirect(request.getContextPath() + "/");
    }
}