package com.EkkerArtem.stream.calculator.parser.impl;

import com.EkkerArtem.stream.calculator.parser.Parser;
import com.EkkerArtem.stream.calculator.state.impl.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserImplTest {
    private Parser parser = new ParserImpl();

    @Test
    void simpleParsingTest() {
        parser.addOperator(new Addition());
        parser.setInput("31+21");
        assertEquals("31", parser.nextSign());
        assertEquals("+", parser.nextSign());
        assertEquals("21", parser.nextSign());
        assertFalse(parser.hasNext());
    }

    @Test
    void complexParsingTest() {
        parser.addOperator(new Addition());
        parser.addOperator(new Subtraction());
        parser.addOperator(new Division());
        parser.addOperator(new Multiplication());
        parser.setInput("1+23-42/2*3");
        assertEquals("1", parser.nextSign());
        assertEquals("+", parser.nextSign());
        assertEquals("23", parser.nextSign());
        assertEquals("-", parser.nextSign());
        assertEquals("42", parser.nextSign());
        assertEquals("/", parser.nextSign());
        assertEquals("2", parser.nextSign());
        assertEquals("*", parser.nextSign());
        assertEquals("3", parser.nextSign());
        assertFalse(parser.hasNext());
    }

    @Test
    void invalidMathematicalExpressionTest() {
        parser.addOperator(new Addition());
        assertThrows(NullPointerException.class, () -> parser.nextSign());
        parser.setInput("4/2");
        parser.nextSign();
        assertThrows(IllegalArgumentException.class, () -> parser.nextSign());

        assertThrows(IllegalArgumentException.class, () -> parser.setInput(""));
    }

}