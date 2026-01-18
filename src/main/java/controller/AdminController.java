package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({"/AdminController", "/adm/*"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class AdminController extends HttpServlet {
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

        System.out.println("YOU ENTERED 'AdminController'");

        String url = request.getPathInfo();
        System.out.println("요청주소 : " + url);

        if (url == null || url.equals("/")) {
            url = "/main.do";
        }

        String page = "/admin/index.jsp"; // DEFLAULT PAGE

        switch (url) {

            case "/main.do":
                page = "/admin/index.jsp";
                break;

            case "/goodssh":
                // TODO: PLANNING FOR PAGE MANAGEMENT
                page = "/admin/index.jsp";
                break;

            default:
                // RETURN TO MAIN PAGE FOR UNRECOGNIZED URLS
                page = "/admin/index.jsp";
                break;
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}