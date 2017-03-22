package com.github.ukslim;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Checker {

    private static final char OPEN = '(';
    private static final char CLOSE = ')';

    public void findSubstrings(String s, BiConsumer<Integer, Integer> handler) {
        bal(s.toCharArray(), handler);
    }

    private void bal(char[] chars, BiConsumer<Integer, Integer> handler) {

        Map<Integer, List<Integer>> starts = new HashMap<>();

        int level = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == OPEN) {
                recordOpen(starts, i, ++level);
            } else if (c == CLOSE) {
                handleClose(starts, i, level--, handler);
            }
        }
    }

    private void recordOpen(Map<Integer, List<Integer>> starts, int pos, int level) {

        starts.merge(level, Collections.singletonList(pos),
                (a, b) -> Stream.concat(a.stream(), b.stream()).collect(Collectors.toList()));

    }

    private void handleClose(Map<Integer, List<Integer>> starts, int closePos, int level,
            BiConsumer<Integer, Integer> handler) {
        List<Integer> startPositions = starts.get(level);
        startPositions.forEach((start) -> {
            handler.accept(start, closePos);
        });

    }

}
