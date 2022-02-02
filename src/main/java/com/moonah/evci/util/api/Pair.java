package com.moonah.evci.util.api;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Pair {
    private String key;
    private Object value;

    public Pair(String key, Object value) {
        this.key = key.trim();
        this.value = value;

        if (this.value instanceof String) {
            this.value = ((String) this.value).trim();
        }
    }

    @Override
    public String toString() {
        return "Key: " + key + ", Value: " + value;
    }
}
