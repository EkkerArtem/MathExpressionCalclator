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
    void simpleParsingTest(){
        parser.addOperator(new Addition());
        parser.setInput("31+21");
        assertEquals("31", parser.nextToken());
        assertEquals("+", parser.nextToken());
        assertEquals("21", parser.nextToken());
        assertFalse(parser.hasNext());
    }

    @Test
    void complexParsingTest(){
        parser.addOperator(new Addition());
        parser.addOperator(new Subtraction());
        parser.addOperator(new Division());
        parser.addOperator(new Multiplication());
        parser.setInput("1+23-42/2*3");
        assertEquals("1", parser.nextToken());
        assertEquals("+", parser.nextToken());
        assertEquals("23", parser.nextToken());
        assertEquals("-", parser.nextToken());
        assertEquals("42", parser.nextToken());
        assertEquals("/", parser.nextToken());
        assertEquals("2", parser.nextToken());
        assertEquals("*", parser.nextToken());
        assertEquals("3", parser.nextToken());
        assertFalse(parser.hasNext());
    }

    @Test
    void invalidMathematicalExpressionTest(){
        parser.addOperator(new Addition());
        assertThrows(NullPointerException.class, () -> parser.nextToken());
        parser.setInput("4/2");
        parser.nextToken();
        assertThrows(IllegalArgumentException.class, () -> parser.nextToken());

        assertThrows(IllegalArgumentException.class, () -> parser.setInput(""));
    }

}