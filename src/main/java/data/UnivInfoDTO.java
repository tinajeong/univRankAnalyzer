package main.java.data;

public class UnivInfoDTO {
    String name;
    String address;
    String website;
    String summary;

    public UnivInfoDTO() {
    }

    public UnivInfoDTO(String name, String address, String website, String summary) {
        this.name = name;
        this.address = address;
        this.website = website;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
