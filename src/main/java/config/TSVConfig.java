package main.java.config;

import java.util.ArrayList;
import java.util.List;

public class TSVConfig {
    //TODO 더 크롤링해오고 싶은 정보 Column 추가하기
    public static final String ColumnUnivName ="name";
    public static final String ColumnUnivRank ="rank";
    public static final String ColumnUnivCountry ="country";
    public static final String crawledTsvPath ="generated/files/output-univrank.tsv";
    public static final String wordTsvPath ="generated/files/output-onlinerandomtools.tsv";

    private static List<String> univColumns;
    private TSVConfig(){
        univColumns = new ArrayList<>();
        setUnivColumns();
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

    public void setUnivColumns(List<String> univColumns)
    {
        this.univColumns.clear();
        this.univColumns.addAll(univColumns);
    }

    public static List<String> getUnivColumns() {
        return univColumns;
    }
}
