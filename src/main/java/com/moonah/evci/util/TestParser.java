package com.moonah.evci.util;

import java.util.List;
import java.util.Map;

public class TestParser {
    public static void main(String[] args) {
        ApiParser apiParser = ApiParser.newInstance();

        List<XmlItem> parsed = apiParser.parse(
                "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo",
                new Pair("pageNo", 1),
                new Pair("numOfRow", 10),
                new Pair("zcode", 11));

//        List<XmlItem> parsed = apiParser.parse(
//                "http://apis.data.go.kr/B552584/EvCharger/getChargerStatus",
//                new Pair("pageNo", 1),
//                new Pair("numOfRow", 10),
//                new Pair("period", 5),
//                new Pair("zcode", 11));

        for (XmlItem xmlItem : parsed) {
            for (Map.Entry<String, String> entry : xmlItem) {
                System.out.println(entry);
            }
            System.out.println();
        }
    }
}
