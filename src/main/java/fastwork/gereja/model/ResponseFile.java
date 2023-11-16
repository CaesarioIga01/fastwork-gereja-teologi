package fastwork.gereja.model;

public class ResponseFile {
    private String nameList;
    private String url;
    private String type;
//    private long size;

    public ResponseFile(String nameList, String url, String type, long size) {
        this.nameList = nameList;
        this.url = url;
        this.type = type;
//        this.size = size;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
