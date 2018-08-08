package com.EkkerArtem.stream.calculator.parser.impl;

import com.EkkerArtem.stream.calculator.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserImplTest {
    private Parser parser = new ParserImpl();

    @Test
    void simpleParsingTest() {
        parser.setInput("31+21");
        assertEquals("31", parser.nextSign());
        assertEquals("+", parser.nextSign());
        assertEquals("21", parser.nextSign());
        assertFalse(parser.hasNext());
    }

    @Test
    void complexParsingTest() {
        parser.setInput("5-99/3*3+5");
        assertEquals("5", parser.nextSign());
        assertEquals("-", parser.nextSign());
        assertEquals("99", parser.nextSign());
        assertEquals("/", parser.nextSign());
        assertEquals("3", parser.nextSign());
        assertEquals("*", parser.nextSign());
        assertEquals("3", parser.nextSign());
        assertEquals("+", parser.nextSign());
        assertEquals("5", parser.nextSign());
        assertFalse(parser.hasNext());
    }

    @Test
    void invalidMathematicalExpressionTest() {
        parser.addOperator("+");
        assertThrows(NullPointerException.class, () -> parser.nextSign());
        parser.setInput("4.2");
        parser.nextSign();
        assertThrows(IllegalArgumentException.class, () -> parser.nextSign());

        assertThrows(IllegalArgumentException.class, () -> parser.setInput(""));
    }

}