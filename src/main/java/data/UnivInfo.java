package main.java.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="univ_info")
public class UnivInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "info_id")
    private String info_id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String website;
    //@Column(length=1000)
    @Column(columnDefinition="text")
    private String summary;

    @ManyToOne(targetEntity = UnivRank.class,fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    UnivRank univRank;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
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

    @Override
    public String toString() {
        return "UnivInfo{" +
                "info_id=" + info_id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", website='" + website + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
