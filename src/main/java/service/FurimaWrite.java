package service;

import model.FurimaDao;
import model.FurimaDto;
import model.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class FurimaWrite implements Command {

    @Override
    public void doCommand(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int userId = (int) request.getSession().getAttribute("loginUserId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));

        Part imagePart = request.getPart("image");
        
        String imagePath = null;

        if (imagePart != null && imagePart.getSize() > 0) {
            String originalName =
                Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();

            String savedName = UUID.randomUUID() + "_" + originalName;

            String uploadDirPath = "/Users/parkminseon/Desktop/upload/furima";
            
            File uploadDir = new File(uploadDirPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File savedFile = new File(uploadDir, savedName);
            imagePart.write(savedFile.getAbsolutePath());

            imagePath = "/upload/furima/" + savedName;
        }

        FurimaDto dto = new FurimaDto();
        
        dto.setUserId(userId);
        dto.setTitle(title);
        dto.setDescription(description);
        dto.setPrice(price);
        dto.setImagePath(imagePath);
        // status will be handled later (default DB value)

        FurimaDao dao = new FurimaDao();
        dao.insert(dto);

        response.sendRedirect(request.getContextPath() + "/furima/list.do");
    }
}