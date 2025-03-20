import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseStringTest {

    @Test
    void testReverse() {
        assertEquals("cba", ReverseString.reverse("abc"));
    }
}