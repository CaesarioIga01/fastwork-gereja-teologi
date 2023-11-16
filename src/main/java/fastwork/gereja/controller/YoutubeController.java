package fastwork.gereja.controller;

import fastwork.gereja.entity.Youtube;
import fastwork.gereja.model.*;
import fastwork.gereja.service.YoutubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class YoutubeController {

    @Autowired
    private YoutubeService service;

    @GetMapping("/youtube/{id}")
    public WebResponse<YoutubeResponse> get(@RequestParam("id")Long id) {

        Youtube youtube = new Youtube();
        youtube.setId(id);
        YoutubeResponse youtubeResponse = service.get(youtube);
        return WebResponse.<YoutubeResponse>builder().data(youtubeResponse).build();
    }
}
