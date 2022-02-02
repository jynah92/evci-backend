package com.moonah.evci.util.api;

import org.springframework.stereotype.Component;
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

@Component
public class ApiXmlParser {
    public List<XmlItem> parse(String url, String xpath, Pair... pairs) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<XmlItem> parsed = new ArrayList<>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(getXmlFromUrl(url, pairs));
            NodeList itemNodeList = makeNodeList(document, xpath);

            for (int i = 0; i < itemNodeList.getLength(); i++) {
                NodeList childNodes = itemNodeList.item(i).getChildNodes();
                parsed.add(new XmlItem(childNodes));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return parsed;
    }

    public int getCount(String url, String countXpath, Pair... pairs) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(getXmlFromUrl(url, pairs));
            Node countNode = makeNodeList(document, countXpath).item(0);

            return Integer.parseInt(countNode.getTextContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
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
        StringBuilder sb = new StringBuilder();

        for (Pair pair : pairs) {
            sb.append("&").append(pair.getKey()).append("=").append(pair.getValue());
        }

        sb.replace(0, 1, "?");

        return url + sb;
    }

    private NodeList makeNodeList(Document document, String path) throws XPathExpressionException {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        XPathExpression expression = xpath.compile(path);
        return (NodeList) expression.evaluate(document, XPathConstants.NODESET);
    }
}
