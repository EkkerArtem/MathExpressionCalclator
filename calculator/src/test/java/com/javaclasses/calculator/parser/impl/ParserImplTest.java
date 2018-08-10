package com.javaclasses.calculator.parser.impl;

import com.javaclasses.finiteStateMachine.Parser;
import com.javaclasses.calculator.impl.parser.impl.ParserImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserImplTest {

    @Test
    void simpleParsingTest() {
        Parser parser = new ParserImpl("31+21");
        assertEquals("31", parser.nextSign(), "first number parse test failed");
        assertEquals("+", parser.nextSign(), "operand parse test failed");
        assertEquals("21", parser.nextSign(), "second number parse test failed");
        assertFalse(parser.hasNext());
    }

    @Test
    void complexParsingTest() {
        Parser parser = new ParserImpl("5-99/3*3+5");
        assertEquals("5", parser.nextSign(), "number parse test failed");
        assertEquals("-", parser.nextSign(), "subtraction parse test failed");
        assertEquals("99", parser.nextSign(), "number parse test failed");
        assertEquals("/", parser.nextSign(), "division parse test failed");
        assertEquals("3", parser.nextSign(), "number parse test failed");
        assertEquals("*", parser.nextSign(), "multiplication parse test failed");
        assertEquals("3", parser.nextSign(), "number parse test failed");
        assertEquals("+", parser.nextSign(), "addition parse test failed");
        assertEquals("5", parser.nextSign(), "number parse test failed");
        assertFalse(parser.hasNext());
    }

    @Test
    void invalidMathematicalExpressionTest() {
        Parser parser = new ParserImpl("4^2");
        parser.addOperator("+");
        parser.nextSign();
        assertThrows(IllegalArgumentException.class, () -> parser.nextSign(), "invalid math expression test failed (resolved ^ sign)");
    }

}
