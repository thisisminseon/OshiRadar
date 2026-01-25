package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.BoardDao;
import model.BoardDto;
import model.BoardCommentDto;
import model.UserDto;

@WebServlet("/board/*")
@MultipartConfig
public class BoardController extends HttpServlet {

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

        String uri = request.getPathInfo();
        if (uri == null || "/".equals(uri)) {
            uri = "/list.do";
        }

        BoardDao dao = new BoardDao();

        switch (uri) {

        // board list
        case "/list.do": {
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }

            String type = request.getParameter("type");
            String keyword = request.getParameter("keyword");

            List<BoardDto> list = dao.getList(page, type, keyword);
            int totalPage = dao.getTotalPage(type, keyword);

            request.setAttribute("list", list);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);

            request.getRequestDispatcher("/boardview/list.jsp")
                   .forward(request, response);
            return;
        }

        // board detail
        case "/detail.do": {
            int postId = Integer.parseInt(request.getParameter("postId"));

            BoardDto dto = dao.getPost(postId);
            if (dto == null) {
                response.sendRedirect(request.getContextPath() + "/board/list.do");
                return;
            }

            dao.increaseViewCount(postId);
            List<BoardCommentDto> commentList = dao.getCommentList(postId);

            request.setAttribute("dto", dto);
            request.setAttribute("commentList", commentList);

            request.getRequestDispatcher("/boardview/detail.jsp")
                   .forward(request, response);
            return;
        }

        // write page
        case "/write.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            request.getRequestDispatcher("/boardview/write.jsp")
                   .forward(request, response);
            return;
        }

        // write process
        case "/writeOk.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            String imagePath = null;
            Part imagePart = request.getPart("image");

            if (imagePart != null && imagePart.getSize() > 0) {
                String original =
                    Paths.get(imagePart.getSubmittedFileName())
                         .getFileName().toString();

                String saved = UUID.randomUUID() + "_" + original;

                String uploadDir = "/Users/parkminseon/Desktop/upload/board";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                imagePart.write(uploadDir + "/" + saved);
                imagePath = "/upload/board/" + saved;
            }

            BoardDto dto = new BoardDto();
            dto.setUserId(loginUser.getUserId());
            dto.setTitle(title);
            dto.setContent(content);
            dto.setImagePath(imagePath);

            dao.insert(dto);

            response.sendRedirect(request.getContextPath() + "/board/list.do");
            return;
        }

        // edit page
        case "/edit.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            BoardDto dto = dao.getPost(postId);

            if (dto == null || dto.getUserId() != loginUser.getUserId()) {
                response.sendRedirect(request.getContextPath() + "/board/list.do");
                return;
            }

            request.setAttribute("dto", dto);
            request.getRequestDispatcher("/boardview/edit.jsp")
                   .forward(request, response);
            return;
        }

        // edit process
        case "/editOk.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            BoardDto dto = dao.getPost(postId);

            if (dto == null || dto.getUserId() != loginUser.getUserId()) {
                response.sendRedirect(request.getContextPath() + "/board/list.do");
                return;
            }

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            String imagePath = dto.getImagePath();
            Part imagePart = request.getPart("image");

            if (imagePart != null && imagePart.getSize() > 0) {
                if (imagePath != null) {
                    File oldImg = new File("/Users/parkminseon/Desktop" + imagePath);
                    if (oldImg.exists()) oldImg.delete();
                }

                String original =
                    Paths.get(imagePart.getSubmittedFileName())
                         .getFileName().toString();

                String saved = UUID.randomUUID() + "_" + original;

                String uploadDir = "/Users/parkminseon/Desktop/upload/board";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                imagePart.write(uploadDir + "/" + saved);
                imagePath = "/upload/board/" + saved;
            }

            dto.setTitle(title);
            dto.setContent(content + "\n\n(修正済み)");
            dto.setImagePath(imagePath);

            dao.update(dto);

            response.sendRedirect(
                request.getContextPath() + "/board/detail.do?postId=" + postId
            );
            return;
        }

        // comment write
        case "/commentWrite.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            String content = request.getParameter("content");

            String imagePath = null;
            Part imagePart = request.getPart("image");

            if (imagePart != null && imagePart.getSize() > 0) {
                String original =
                    Paths.get(imagePart.getSubmittedFileName())
                         .getFileName().toString();

                String saved = UUID.randomUUID() + "_" + original;

                String uploadDir = "/Users/parkminseon/Desktop/upload/board/comment";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                imagePart.write(uploadDir + "/" + saved);
                imagePath = "/upload/board/comment/" + saved;
            }

            dao.insertComment(postId, loginUser.getUserId(), content, imagePath);

            response.sendRedirect(
                request.getContextPath() + "/board/detail.do?postId=" + postId
            );
            return;
        }

        // board delete
        case "/delete.do": {
            HttpSession session = request.getSession();
            UserDto loginUser = (UserDto) session.getAttribute("loginUser");

            if (loginUser == null) {
                response.sendRedirect(request.getContextPath() + "/users/login.do");
                return;
            }

            int postId = Integer.parseInt(request.getParameter("postId"));
            BoardDto dto = dao.getPost(postId);

            if (dto == null || dto.getUserId() != loginUser.getUserId()) {
                response.sendRedirect(request.getContextPath() + "/board/list.do");
                return;
            }

            if (dto.getImagePath() != null) {
                File img = new File("/Users/parkminseon/Desktop" + dto.getImagePath());
                if (img.exists()) img.delete();
            }

            dao.delete(postId);

            response.sendRedirect(request.getContextPath() + "/board/list.do");
            return;
        }

        default:
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}