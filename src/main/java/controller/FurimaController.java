package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.FurimaDao;
import model.FurimaDto;
import model.UserDto;

@WebServlet("/furima/*")
public class FurimaController extends HttpServlet {

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
        if (uri == null || "/".equals(uri)) {
            uri = "/list.do";
        }
        FurimaDao dao = new FurimaDao();
        String page = null;

        switch (uri) {

        // List of Products
        case "/list.do":
        case "/productlist": {
            List<FurimaDto> list = dao.findAll();
            request.setAttribute("furimaList", list);
            page = "/furima/productlist.jsp";
            break;
        }

        // Forms for Adding a Product
        case "/write.do": {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }
            page = "/furima/write.jsp";
            break;
        }

        // Process Adding a Product
        case "/writeOk.do": {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            FurimaDto dto = new FurimaDto();
            dto.setUserId(loginUser.getUserId());
            dto.setTitle(request.getParameter("title"));
            dto.setDescription(request.getParameter("description"));

            try {
                dto.setPrice(Integer.parseInt(request.getParameter("price")));
            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/furima/write.do");
                return;
            }

            dto.setImagePath(request.getParameter("image_path"));
            dto.setStatus('Y');

            dao.insert(dto);

            response.sendRedirect(request.getContextPath() + "/furima/list.do");
            return;
        }

        // Detail View of a Product
        case "/detail.do": {
            String param = request.getParameter("postId");
            if (param == null) {
                response.sendRedirect(request.getContextPath() + "/furima/list.do");
                return;
            }

            int postId = Integer.parseInt(param);
            FurimaDto dto = dao.findById(postId);

            if (dto == null) {
                response.sendRedirect(request.getContextPath() + "/furima/list.do");
                return;
            }

            request.setAttribute("furima", dto);
            page = "/furima/detail.jsp";
            break;
        }

        // List of User's Products
        case "/mylist.do": {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            UserDto loginUser = (UserDto) session.getAttribute("loginUser");
            List<FurimaDto> list = dao.findByUser(loginUser.getUserId());

            request.setAttribute("furimaList", list);
            page = "/furima/mylist.jsp";
            break;
        }

       // Update Product Status to Sold Out
        case "/status.do": {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("loginUser") == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            dao.updateStatus(postId, 'N');

            response.sendRedirect(request.getContextPath() + "/furima/mylist.do");
            return;
        }

        
        // Default Case
        default:
            response.sendRedirect(request.getContextPath() + "/furima/list.do");
            return;
        }

        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}