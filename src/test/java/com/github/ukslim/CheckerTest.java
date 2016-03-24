package com.github.ukslim;

import java.util.*;
import java.util.function.BiConsumer;
import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CheckerTest
{
    
    Checker checker = new Checker();
    int count;
    BiConsumer<Integer, Integer> addToCount = ( start, end ) -> {
        count ++;
    };
    
    @Before
    public void resetCount() {
        count = 0;
    }
    
    @Test
    public void simplest_case_is_1() {
        checker.findSubstrings("()", addToCount);
        assertEquals(1, count);
    }
    
    @Test
    public void two_in_a_row_is_3() {
        checker.findSubstrings("()()", addToCount);
        assertEquals(3, count);
    }
    
    @Test
    public void three_in_a_row_is_6() {
        checker.findSubstrings("()()()", addToCount);
        assertEquals(6, count);
    }
    
    @Test
    public void two_nested_is_2() {
        checker.findSubstrings("(())", addToCount);
        assertEquals(2, count);
    }
    
    @Test
    public void three_nested_is_3() {
        checker.findSubstrings("((()))", addToCount);
        assertEquals(3, count);
    }
    
    @Test
    public void complex_case() {
        checker.findSubstrings("(()((()()(())()))", addToCount);
        assertEquals(15, count);
    }
    
    @Test
    public void check_results() {
        final String input = "(()((()()(())())))";
        List<String> substrings = new ArrayList<>();
        BiConsumer<Integer,Integer> handler = (start,end) -> {
            substrings.add(makeDisplayString(input, start, end));
        };
        
        checker.findSubstrings(input, handler);
        
        List<String> expected = Arrays.asList(
                "(()((()()(())())))" ,
                " ()               " ,
                " ()((()()(())())) " ,
                "   ((()()(())())) " ,
                "    (()()(())())  " ,
                "     ()           " ,
                "     ()()         " ,
                "     ()()(())     " ,
                "     ()()(())()   " ,
                "       ()         " ,
                "       ()(())     " ,
                "       ()(())()   " ,
                "         (())     " ,
                "         (())()   " ,
                "          ()      " ,
                "             ()   ");
        assertThat(substrings, is(expected));
    }

    private String makeDisplayString(String input, Integer start, Integer end) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            s.append((i>=start && i<=end ) ? input.charAt(i) : ' ');
        }
        final String displayString = s.toString();
        return displayString;
    }
}
