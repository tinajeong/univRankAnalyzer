package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class TSVConfig {
    //TODO 더 크롤링해오고 싶은 정보 Column 추가하기
    public static final String ColumnUnivName ="name";
    public static final String ColumnUnivRank ="rank";
    public static final String ColumnUnivCountry ="country";
    public static final String crawledTsvPath ="output-univrank.tsv";
    public static final String wordTsvPath ="output-onlinerandomtools.tsv";

    private static List<String> univColumns;
    private TSVConfig(){
        univColumns = new ArrayList<>();
        setUnivColumns();
    }
    //TODO 싱글톤 패턴 적용하기
    private void setUnivColumns(){
        //TODO add 함수 쓰기
    }

    public void setUnivColumns(List<String> univColumns)
    {
        //TODO 구현하고 다이나믹하게 헤더 이름 정해주기
    }

    public static List<String> getUnivColumns() {
        return univColumns;
    }
}
