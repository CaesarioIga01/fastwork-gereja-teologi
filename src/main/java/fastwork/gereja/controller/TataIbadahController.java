package fastwork.gereja.controller;

import fastwork.gereja.entity.TataIbadah;
import fastwork.gereja.model.ResponseFile;
import fastwork.gereja.model.WebResponse;
import fastwork.gereja.repository.TataIbadahRepository;
import fastwork.gereja.service.TataIbadahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class TataIbadahController {

    @Autowired
    private TataIbadahService tataIbadahService;

    @Autowired
    private TataIbadahRepository repository;

    @GetMapping("/files")
    public WebResponse<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = tataIbadahService.getAllFiles().map(tataIbadah -> {
            String fileDownloadUri = (ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(tataIbadah.getId()))
                    .toUriString());

            return new ResponseFile(
                    tataIbadah.getNameList(),
                    fileDownloadUri,
                    tataIbadah.getType(),
                    tataIbadah.getData().length);
        }).toList();

        return WebResponse.<List<ResponseFile>>builder().data(files).build();
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        TataIbadah tataIbadah = tataIbadahService.getFile(id);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "inline; filename=\"" + tataIbadah.getNameList() + "\"");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(tataIbadah.getData());
    }
}
