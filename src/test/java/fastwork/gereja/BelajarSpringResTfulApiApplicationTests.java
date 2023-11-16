package fastwork.gereja;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import fastwork.gereja.entity.Documents;
import fastwork.gereja.repository.DocumentRepository;
import static org.assertj.core.api.Assertions.assertThat;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BelajarSpringResTfulApiApplicationTests {

	@Autowired
	private DocumentRepository repository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	@Rollback(value = false)
	void testInsertDocument() throws IOException {

		File file = new File("/Users/caesarioigaalkowi/Desktop/Caesario Iga Alkowi-resume.pdf");

		Documents documents = new Documents();

		documents.setName(file.getName());

		byte[] bytes = Files.readAllBytes(file.toPath());
		documents.setContent(bytes);
		long fileSize = bytes.length;
		documents.setSize(fileSize);
		documents.setUploadTime(new Date());


		Documents save = repository.save(documents);

		Documents existDocs = testEntityManager.find(Documents.class, save.getId());

		assertThat(existDocs.getSize()).isEqualTo(fileSize);

	}

}
