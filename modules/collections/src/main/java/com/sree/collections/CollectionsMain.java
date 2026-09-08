package com.sree.collections;

import com.sree.common.DemoSupport;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CollectionsMain {
    private CollectionsMain() {
    }

    public static void main(String[] args) {
        DemoSupport.printHeader("Collections");

        List<String> topics = Arrays.asList("List", "Set", "Map");
        Map<String, Integer> nameLengths = new LinkedHashMap<String, Integer>();
        for (String topic : topics) {
            nameLengths.put(topic, topic.length());
        }

        System.out.println("Topics and name lengths: " + nameLengths);
    }
}
