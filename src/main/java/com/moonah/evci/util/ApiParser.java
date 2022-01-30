package com.moonah.evci.util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiParser {

    public static final String URL = "http://apis.data.go.kr/B552584/EvCharger/getChargerInfo";
    public static final String SERVICE_KEY = "Ej7lakr%2BHWqzlt%2B5MLe2RivCLKvcBWFaSWjhDtpXGgWhEGuepHea76j6HYh1rj75zet%2B0XpSogcj8E9Jom1bwg%3D%3D";
    public static final int PAGE_NO = 1;
    public static final int NUM_OF_ROWS = 10;
    public static final int ZCODE = 11;

    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(getXmlFromUrl());
            NodeList nodeList = makeNodeList(document);

            for (int i = 0; i < nodeList.getLength(); i++) {
                NodeList child = nodeList.item(i).getChildNodes();

                for (int j = 0; j < child.getLength(); j++) {
                    Node node = child.item(j);
                    System.out.println("현재 노드 이름 : " + node.getNodeName());
                    System.out.println("현재 노드 타입 : " + node.getNodeType());
                    System.out.println("현재 노드 값 : " + node.getTextContent());
                    System.out.println("현재 노드 네임스페이스 : " + node.getPrefix());
                    System.out.println("현재 노드의 다음 노드 : " + node.getNextSibling());
                    System.out.println("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static InputSource getXmlFromUrl() throws IOException {
        URL url = new URL(makeFullUrl());

        System.out.println(makeFullUrl());

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            result.append(line.trim());
        }

        System.out.println(result);

        return new InputSource(new StringReader(result.toString()));
    }

    private static String makeFullUrl() {
        return URL +
                "?serviceKey=" + SERVICE_KEY +
                "&pageNo=" + PAGE_NO +
                "&numOfRows=" + NUM_OF_ROWS +
                "&zcode=" + ZCODE;
    }

    private static NodeList makeNodeList(Document document) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile("//items/item");
        return (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    }
}
