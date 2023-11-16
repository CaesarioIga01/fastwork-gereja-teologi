package fastwork.gereja.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ibadah")
public class TataIbadah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_list")
    private String nameList;

    private String type;

    @Lob
    private byte[] data;

    public TataIbadah() {}

    public TataIbadah(String nameList, String type, byte[] data) {
        this.nameList = nameList;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getNameList() {
        return nameList;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
