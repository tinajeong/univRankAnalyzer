package main.java.data;

public class UnivRankDTO {
    String univName;
    String country;
    Long rank;
    String  univInfoHref;
    public UnivRankDTO() {
    }

    public UnivRankDTO(String univName, String country, Long rank) {
        this.univName = univName;
        this.country = country;
        this.rank = rank;
    }

    public UnivRankDTO(String univName, String country, Long rank, String univInfoHref) {
        this.univName = univName;
        this.country = country;
        this.rank = rank;
        this.univInfoHref = univInfoHref;
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

    public String getUnivInfoHref() {
        return univInfoHref;
    }

    public void setUnivInfoHref(String univInfoHref) {
        this.univInfoHref = univInfoHref;
    }
}
