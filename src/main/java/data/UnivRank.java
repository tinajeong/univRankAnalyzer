package main.java.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UNIV_RANK")
public class UnivRank {
    @Id
    @Column
    private String univName;
    @Column
    private String country;
    @Column
    private int rank;
    @OneToMany(mappedBy="univRank")
    private List<UnivInfo> univInfos = new ArrayList<UnivInfo>();


    public String getUnivName() {
        return univName;
    }

    public void setUnivName(String univName) {
        this.univName = univName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<UnivInfo> getUnivInfos() {
        return univInfos;
    }

    public void setUnivInfos(List<UnivInfo> univInfos) {
        this.univInfos = univInfos;
    }

    @Override
    public String toString() {
        return "UnivRank{" +
                "univName='" + univName + '\'' +
                ", country='" + country + '\'' +
                ", rank=" + rank +
                '}';
    }
}
