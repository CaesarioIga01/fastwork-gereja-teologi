package fastwork.gereja.controller.web;

import fastwork.gereja.entity.TataIbadah;
import fastwork.gereja.repository.TataIbadahRepository;
import fastwork.gereja.service.TataIbadahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TataIbadahWebController {

    @Autowired
    private TataIbadahService tataIbadahService;

    @Autowired
    private TataIbadahRepository repository;

    @PostMapping("/upload/tata/ibadah")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = "";
        try {
            tataIbadahService.store(file);

            message = "Uploaded the file succesfully: " + file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("message", "The file has been uploaded successfully " + file.getOriginalFilename());
            return "redirect:/tata-ibadah";
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            redirectAttributes.addFlashAttribute("message", "Could not upload the file: " + file.getOriginalFilename());
            return "redirect:/tata-ibadah";
        }
    }

    @GetMapping("/tata-ibadah")
    public String viewTataIbadah(Model model) {

        List<TataIbadah> listTataIbadah = tataIbadahService.getTataIbadah();
        model.addAttribute("listTataIbadah", listTataIbadah);
        return "tata-ibadah";
    }

    @GetMapping("/delete/tata-ibadah/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/tata-ibadah";
    }
}