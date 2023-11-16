package fastwork.gereja.controller.web;

import fastwork.gereja.entity.Documents;
import fastwork.gereja.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class KidungLaguWebController {

    @Autowired
    private DocumentRepository repository;

    @GetMapping("/kidung-lagu")
    public String viewKidungLagu(Model model) {

        List<Documents> listDocs = repository.findAll();
        model.addAttribute("listDocs", listDocs);
        return "kidung-lagu";
    }

    @GetMapping("/delete/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/kidung-lagu";
    }
}
