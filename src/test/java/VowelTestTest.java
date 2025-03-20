import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VowelTestTest {

    @Test
    void testVowel() {
        assertTrue(VowelTest.testVowel("cat"));
        assertFalse(VowelTest.testVowel("ct"));
    }
}