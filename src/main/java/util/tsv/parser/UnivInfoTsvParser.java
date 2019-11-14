package main.java.util.tsv.parser;

import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import main.java.config.TSVConfig;
import main.java.data.dto.UnivInfoDTO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UnivInfoTsvParser extends BasicTsvParser {
    List<UnivInfoDTO> univInfoDTOList;
    public UnivInfoTsvParser() {
        super();
        univInfoDTOList = new ArrayList<>();
    }

    public UnivInfoTsvParser(String tsvPath) {
        super(tsvPath);
    }

    @Override
    public void readTSV() {
        TsvParserSettings settings = new TsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        TsvParser parser = new TsvParser(settings);
        if (TsvPath == null)
            allRows = parser.parseAll(new File(TSVConfig.getInstance().crawledInfoTsvPath));
        else
            allRows = parser.parseAll(new File(TsvPath));

        for (String[] strArr : allRows) {
            if (allRows.indexOf(strArr) == 0)
                continue;
            UnivInfoDTO univInfoDTO = new UnivInfoDTO(strArr[0], strArr[1], strArr[2],strArr[3]);
            univInfoDTOList.add(univInfoDTO);
        }
    }

    public List<UnivInfoDTO> getUnivInfoDTOList() {
        return univInfoDTOList;
    }

    public void setUnivInfoDTOList(List<UnivInfoDTO> univInfoDTOList) {
        this.univInfoDTOList = univInfoDTOList;
    }

}
