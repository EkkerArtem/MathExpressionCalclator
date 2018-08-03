import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamInterpreterImplTest {
    private StreamInterpreterImpl interpreter;

    @BeforeEach
    void init() {
        interpreter = new StreamInterpreterImpl();
    }

    /**
     * Testing the arithmetical operations together.
     */
    @Test
    void interpretAll() {
        assertEquals(13, interpreter.interpret("1+8*2-18/4"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void interpretAddition() {
        assertEquals(16, interpreter.interpret("5+11"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void interpretSubstraction() {
        assertEquals(2, interpreter.interpret("5-3"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void interpretMultiplication() {
        assertEquals(9, interpreter.interpret("3*3"));

    }

    /**
     * Testing the addition.
     */
    @Test
    void interpretDivion() {
        assertEquals(4, interpreter.interpret("16/4"));

    }

    /**
     * Testing the letters illegal arguments.
     */
    @Test
    void LettersInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> interpreter.interpret("1+dasd8*dasd2-18fewfewf/4fwefwefew"));

    }

    /**
     * Testing the symbols illegal arguments.
     */
    @Test
    void SymbolsInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> interpreter.interpret("1+&8*{}2-18[]/4#$@"));
    }

    /**
     * Testing the spaces illegal arguments.
     */
    @Test
    void SpacesInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> interpreter.interpret("1 + 8*2-18/4"));
    }


}