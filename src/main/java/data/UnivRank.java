package main.java.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UnivRank {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String univName;
    @Column
    private String country;
    @Column
    private int rank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "UnivRank{" +
                "id=" + id +
                ", univName='" + univName + '\'' +
                ", country='" + country + '\'' +
                ", rank=" + rank +
                '}';
    }
}
