package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.FurimaDao;
import model.FurimaDto;

@WebServlet("/furimarket/*")
public class FurimaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    static {
        System.out.println("FurimaController LOADED");
    }

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
        
        System.out.println("===== FURIMARKET DEBUG =====");
        System.out.println("requestURI  = " + request.getRequestURI());
        System.out.println("contextPath = " + request.getContextPath());
        System.out.println("servletPath = " + request.getServletPath());
        System.out.println("pathInfo    = " + request.getPathInfo());
        System.out.println("============================");

        String uri = request.getPathInfo();
        System.out.println("▶ resolved uri = " + uri);

        if (uri == null || "/".equals(uri)) {
            uri = "/list.do";
            System.out.println("▶ uri corrected to /list.do");
        }

        switch (uri) {

        case "/list.do":
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            int pageSize = 10;
            int start = (page - 1) * pageSize + 1;
            int end = page * pageSize;

            FurimaDao dao = new FurimaDao();
            List<FurimaDto> list = dao.getList(start, end);
            int totalCount = dao.getTotalCount();
            int totalPage = (int)Math.ceil(totalCount / 10.0);

            request.setAttribute("list", list);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);

            request.getRequestDispatcher("/furimarket/list.jsp").forward(request, response);
            break;

            case "/write.do":
                forward(request, response, "/furimarket/write.jsp");
                break;

            case "/detail.do":
                forward(request, response, "/furimarket/detail.jsp");
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}