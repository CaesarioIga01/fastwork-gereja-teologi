package fastwork.gereja.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fastwork.gereja.entity.Documents;
import fastwork.gereja.repository.DocumentRepository;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Controller
public class DownloadAndUploadController {

    @Autowired
    private DocumentRepository repository;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("document")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        Documents documents = new Documents();
        documents.setName(fileName);
        documents.setContent(multipartFile.getBytes());
        documents.setSize(multipartFile.getSize());
        documents.setUploadTime(new Date());

        repository.save(documents);

        redirectAttributes.addFlashAttribute("message", "The file has been uploaded successfully");

        return "redirect:/kidung-lagu";
    }

    @GetMapping("/download")
    public void downloadFile(@Param("id") Long id, HttpServletResponse response) throws Exception {
        Optional<Documents> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new Exception("Could not find document with id: " + id);
        }
        Documents documents = result.get();

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + documents.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(documents.getContent());
        outputStream.close();
    }
}