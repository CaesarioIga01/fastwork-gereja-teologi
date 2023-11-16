package fastwork.gereja.service;

import fastwork.gereja.entity.TataIbadah;
import fastwork.gereja.repository.TataIbadahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class TataIbadahService {

    @Autowired
    private TataIbadahRepository tataIbadahRepository;

    public TataIbadah store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        TataIbadah tataIbadah = new TataIbadah(fileName, file.getContentType(), file.getBytes());

        return tataIbadahRepository.save(tataIbadah);
    }

    public List<TataIbadah> getTataIbadah() {
        return tataIbadahRepository.findAll();
    }

    public TataIbadah getFile(Long id) {
        return tataIbadahRepository.findById(id).get();
    }

    public Stream<TataIbadah> getAllFiles() {
        return tataIbadahRepository.findAll().stream();
    }

    public void remove(Long id) {
        tataIbadahRepository.deleteById(id);
    }
}
