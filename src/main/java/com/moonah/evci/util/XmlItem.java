package com.moonah.evci.util;

import lombok.NoArgsConstructor;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

@NoArgsConstructor
public class XmlItem implements Map<String, String>, Iterable<Map.Entry<String, String>> {
    private final Map<String, String> map = new LinkedHashMap<>();

    public XmlItem(NodeList nodeList) {
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            map.put(node.getNodeName(), node.getTextContent());
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return map.get(key);
    }

    @Override
    public String put(String key, String value) {
        return map.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<String> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, String>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return map.entrySet().iterator();
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
