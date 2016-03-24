package com.github.ukslim;

import java.util.function.BiConsumer;

public class Checker
{
    public void findSubstrings(String s, BiConsumer<Integer,Integer> handler) 
    {
        bal(s.toCharArray(), handler);
    }
    
    private void bal(char[] chars, BiConsumer<Integer,Integer> handler) {
        for(int i=0; i<chars.length; i++) {
            findClosesAtThisLevel(chars, i, handler);
        }
    }
    
    private void findClosesAtThisLevel(char[] chars, int pos, BiConsumer<Integer,Integer> handler) {
        if(chars[pos] == ')') {
            return;
        } else {
            int level = 0;
            for(int i = pos; i<chars.length; i++) {
                if(chars[i] == '(') {
                    level++;
                }
                if(chars[i] == ')') {
                    level--;
                    if(level == 0) {
                        handler.accept(pos, i);
                    }
                    if(level < 0) {
                        // reached beyond scope
                        break; 
                    }
                }
            }

        }
    }
}
