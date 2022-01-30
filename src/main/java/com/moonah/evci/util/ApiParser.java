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
import java.util.ArrayList;
import java.util.List;

import static com.moonah.evci.util.Constants.*;

public class ApiParser {
    private ApiParser() {
    }

    public static ApiParser newInstance() {
        return new ApiParser();
    }

    public List<XmlItem> parse(String url, Pair... pairs) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<XmlItem> parsed = new ArrayList<>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(getXmlFromUrl(url, pairs));
            NodeList headerNodeList = makeNodeList(document, XML_HEADER_XPATH)
                    .item(0).getChildNodes();
            XmlItem header = new XmlItem(headerNodeList);

            System.out.println(header);

            NodeList itemNodeList = makeNodeList(document, XML_ITEMS_XPATH);

            for (int i = 0; i < itemNodeList.getLength(); i++) {
                NodeList childNodes = itemNodeList.item(i).getChildNodes();
                parsed.add(new XmlItem(childNodes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parsed;
    }

    private InputSource getXmlFromUrl(String url, Pair... pairs) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) (new URL(makeFullUrl(url, pairs))).openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            result.append(line.trim());
        }

        return new InputSource(new StringReader(result.toString()));
    }

    private String makeFullUrl(String url, Pair... pairs) {
        StringBuilder sb = new StringBuilder(url + "?serviceKey=" + SERVICE_KEY);

        for (Pair pair : pairs) {
            sb.append("&").append(pair.getKey()).append("=").append(pair.getValue());
        }

        return sb.toString();
    }

    private NodeList makeNodeList(Document document, String path) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile(path);
        return (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    }
}
