package main.java.config;

import java.util.ArrayList;
import java.util.List;

public class TSVConfig {
    //TODO
    public static final String ColumnUnivName ="name";
    public static final String ColumnUnivRank ="rank";
    public static final String ColumnUnivCountry ="country";
    public static final String ColumnUnivAddress ="address";
    public static final String ColumnUnivWebsite ="website";
    public static final String ColumnUnivSummary ="summary";
    public static final String crawledRankTsvPath ="generated/files/output-univrank.tsv";
    public static final String crawledInfoTsvPath ="generated/files/output-univInfo.tsv";
    public static final String wordTsvPath ="generated/files/output-onlinerandomtools.tsv";

    private static List<String> univColumns;
    private static List<String> univInfoColumns;

    private TSVConfig(){
        univColumns = new ArrayList<>();
        univInfoColumns =new ArrayList<>();
        setUnivColumns();
        setUnivInfoColumns();
    }
    private static class LazyHolder {
        public static final TSVConfig INSTANCE = new TSVConfig();
    }
    public static TSVConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    private void setUnivColumns(){
        univColumns.add(ColumnUnivRank);
        univColumns.add(ColumnUnivName);
        univColumns.add(ColumnUnivCountry);
    }
    private void setUnivInfoColumns()
    {
        univInfoColumns.add(ColumnUnivName);
        univInfoColumns.add(ColumnUnivAddress);
        univInfoColumns.add(ColumnUnivWebsite);
        univInfoColumns.add(ColumnUnivSummary);
    }

    public void setUnivColumns(List<String> univColumns)
    {
        this.univColumns.clear();
        this.univColumns.addAll(univColumns);
    }
    public static List<String> getUnivColumns() {
        return univColumns;
    }

    public static List<String> getUnivInfoColumns() {
        return univInfoColumns;
    }
    public  void setUnivInfoColumns(List<String> univInfoColumns) {
        this.univInfoColumns.clear();
        this.univInfoColumns.addAll(univInfoColumns);
    }
}
