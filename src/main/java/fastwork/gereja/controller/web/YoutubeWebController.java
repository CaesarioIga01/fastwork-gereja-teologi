package fastwork.gereja.controller.web;

import fastwork.gereja.entity.Youtube;
import fastwork.gereja.repository.YoutubeRepository;
import fastwork.gereja.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class YoutubeWebController {

    @Autowired
    private YoutubeRepository repository;

    @Autowired
    private YoutubeService service;

    @GetMapping("/youtube")
    public String viewYoutube(Model model) {

        List<Youtube> listYoutube = service.getListYoutube();
        model.addAttribute("listYoutube", listYoutube);
        return "youtube";
    }

    @GetMapping("/youtube/new")
    public String addNewYoutube(Model model) {
        Youtube youtube = new Youtube();
        model.addAttribute("youtube", youtube);
        return "create_youtube";
    }

    @PostMapping("/youtube")
    public String saveYoutube(@ModelAttribute("youtube") Youtube youtube) {
        service.addYoutube(youtube);
        return "redirect:/youtube";
    }

    @GetMapping("/delete/youtube/{id}")
    public String remove(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/youtube";
    }
}