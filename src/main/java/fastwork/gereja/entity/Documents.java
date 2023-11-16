package fastwork.gereja.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Getter
@Entity
@Table(name = "documents")
public class Documents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false, unique = true)
    private String name;

    private Long size;

    @Column(name = "upload_time")
    private Date uploadTime;

    private byte[] content;

    public Documents(Long id, String name, Long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public Documents() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
