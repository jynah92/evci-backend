package com.moonah.evci.util.openapi;

import java.util.List;
import java.util.Map;

import static com.moonah.evci.util.openapi.Constants.*;

public class TestParser {
    public static void main(String[] args) {
        ApiXmlParser apiParser = ApiXmlParser.newInstance();

//        List<XmlItem> parsed = apiParser.parse(
//                "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo",
//                PDP_XML_HEADER_XPATH, PDP_XML_ITEMS_XPATH,
//                new Pair("serviceKey", PDP_SERVICE_KEY),
//                new Pair("pageNo", 1),
//                new Pair("numOfRow", 10),
//                new Pair("zcode", 11));

//        List<XmlItem> parsed = apiParser.parse(
//                "http://apis.data.go.kr/B552584/EvCharger/getChargerStatus",
//                PDP_XML_HEADER_XPATH, PDP_XML_ITEMS_XPATH,
//                new Pair("serviceKey", PDP_SERVICE_KEY),
//                new Pair("pageNo", 1),
//                new Pair("numOfRow", 10),
//                new Pair("period", 5),
//                new Pair("zcode", 11));

        List<XmlItem> parsed = apiParser.parse(
                "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                "//list",
                new Pair("key", KEC_SERVICE_KEY),
                new Pair("type", "xml"),
                new Pair("numOfRow", 10));

        for (XmlItem xmlItem : parsed) {
            for (Map.Entry<String, String> entry : xmlItem) {
                System.out.println(entry);
            }
            System.out.println();
        }
    }
}
