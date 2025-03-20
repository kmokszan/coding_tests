import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

public class VowelTest {
    public static void main(String[] args) {
        System.out.println(testVowel("Hello, World!"));
    }

    static final Set<Integer> vowelsAsIntegers = "aeiou".chars().boxed().collect(Collectors.toSet());

    static boolean testVowel(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Optional<Integer> firstVowel = s.chars().boxed().filter(a -> vowelsAsIntegers.contains(a)).findFirst();
        return firstVowel.isPresent();
    }
}
