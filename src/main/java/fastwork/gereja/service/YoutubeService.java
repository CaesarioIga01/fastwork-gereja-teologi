package fastwork.gereja.service;

import fastwork.gereja.entity.Youtube;
import fastwork.gereja.model.*;
import fastwork.gereja.repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class YoutubeService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private YoutubeRepository repository;

    public Youtube addYoutube(Youtube youtube) {
        return repository.save(youtube);
    }

    public YoutubeResponse get(Youtube youtube) {

        Optional<Youtube> getId = repository.findById(youtube.getId());

        if (!getId.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id not found");
        }

        youtube = getId.get();

        return YoutubeResponse.builder()
                .id(String.valueOf(youtube.getId()))
                .linkYoutube(youtube.getLinkYoutube())
                .build();
    }

    public List<Youtube> getListYoutube() {
        return repository.findAll();
    }
}
