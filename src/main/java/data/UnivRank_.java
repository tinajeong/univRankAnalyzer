package main.java.data;

import main.java.data.UnivRank;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UnivRank.class)
public class UnivRank_ {
    public static volatile SingularAttribute<UnivRank, String> univName;
    public static volatile SingularAttribute<UnivRank, String> country;
    public static volatile SingularAttribute<UnivRank, Integer> rank;
    public static volatile ListAttribute<UnivRank, UnivInfo> totalCost;
}
