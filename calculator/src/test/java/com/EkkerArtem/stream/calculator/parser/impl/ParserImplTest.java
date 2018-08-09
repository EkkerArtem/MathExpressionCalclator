package com.EkkerArtem.stream.calculator.parser.impl;

import com.EkkerArtem.stream.calculator.finiteStateMachine.Parser;
import com.EkkerArtem.stream.calculator.impl.parser.impl.ParserImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserImplTest {

    @Test
    void simpleParsingTest() {
        Parser parser = new ParserImpl("31+21");
        assertEquals("31", parser.nextSign());
        assertEquals("+", parser.nextSign());
        assertEquals("21", parser.nextSign());
        assertFalse(parser.hasNext());
    }

    @Test
    void complexParsingTest() {
        Parser parser = new ParserImpl("5-99/3*3+5");
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
        Parser parser = new ParserImpl("4.2");
        parser.addOperator("+");
        parser.nextSign();
        assertThrows(IllegalArgumentException.class, () -> parser.nextSign());
    }

}
