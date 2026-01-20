package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Command;

public class UserLogout implements Command {

    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get existing session only
        HttpSession session = request.getSession(false);

        // invalidate session if exists
        if (session != null) {
            session.invalidate();
        }

        // redirect to index (root)
        response.sendRedirect(request.getContextPath() + "/");
    }
}