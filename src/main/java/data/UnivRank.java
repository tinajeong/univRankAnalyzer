package main.java.data;

public class UnivRank {
    String univName;
    String country;
    Long rank;

    public UnivRank() {
    }

    public UnivRank(String univName, String country, Long rank) {
        this.univName = univName;
        this.country = country;
        this.rank = rank;
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

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
