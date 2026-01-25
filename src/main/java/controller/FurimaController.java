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

import model.FurimaDao;
import model.FurimaDto;
import model.UserDto;

@MultipartConfig
@WebServlet("/furima/*")
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
			int totalPage = (int) Math.ceil(totalCount / 10.0);

			request.setAttribute("list", list);
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);

			request.getRequestDispatcher("/furimarket/list.jsp").forward(request, response);
			break;

		case "/write.do":
			forward(request, response, "/furimarket/write.jsp");
			break;

		case "/writeOk.do":
		    System.out.println("=== [FURIMA] writeOk.do START ===");

		    // multipart request
		    Part imagePart = request.getPart("image");
		    System.out.println("imagePart = " + imagePart);

		    UserDto loginUser = (UserDto) request.getSession().getAttribute("loginUser");
		    System.out.println("loginUser = " + loginUser);

		    int userId = loginUser.getUserId();
		    String title = request.getParameter("title");
		    String description = request.getParameter("description");
		    int price = Integer.parseInt(request.getParameter("price"));
		    String status = request.getParameter("status");

		    System.out.println("title = " + title);
		    System.out.println("price = " + price);
		    System.out.println("status = " + status);

		    String imagePath = null;

		    if (imagePart != null && imagePart.getSize() > 0) {
		        String original =
		            Paths.get(imagePart.getSubmittedFileName())
		                 .getFileName().toString();

		        String saved = UUID.randomUUID() + "_" + original;

		        String uploadDir = "/Users/parkminseon/Desktop/upload/furima";
		        File dir = new File(uploadDir);
		        if (!dir.exists()) dir.mkdirs();

		        imagePart.write(uploadDir + "/" + saved);

		        imagePath = "/upload/furima/" + saved;
		        System.out.println("image saved : " + imagePath);
		    }

		    FurimaDto dto = new FurimaDto();
		    dto.setUserId(userId);
		    dto.setTitle(title);
		    dto.setDescription(description);
		    dto.setPrice(price);
		    dto.setStatus(status);
		    dto.setImagePath(imagePath);

		    System.out.println("DTO ready, inserting DB...");

		    new FurimaDao().insert(dto);

		    System.out.println("DB INSERT DONE");
		    System.out.println("=== [FURIMA] writeOk.do END ===");

		    response.sendRedirect(request.getContextPath() + "/furima/list.do");
		    break;

		case "/detail.do":
		    int postId = Integer.parseInt(request.getParameter("postId"));

		    FurimaDao detailDao = new FurimaDao();
		    FurimaDto detailDto = detailDao.getPost(postId);

		    if (detailDto == null) {
		        response.sendRedirect(request.getContextPath() + "/furima/list.do");
		        return;
		    }
		    
		    request.setAttribute("dto", detailDto);
		    forward(request, response, "/furimarket/detail.jsp");
		    break;
		    
		case "/delete.do":
		    int postId1 = Integer.parseInt(request.getParameter("postId"));

		    FurimaDao deleteDao = new FurimaDao();
		    deleteDao.delete(postId1);

		    response.sendRedirect(
		        request.getContextPath() + "/furima/list.do?msg=deleted"
		    );
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