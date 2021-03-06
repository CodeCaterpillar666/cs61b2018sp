import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertFalse(offByOne.equalChars('a', 'a'));
        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('c', 'a'));
        assertFalse(offByOne.equalChars('A', 'b'));
        // according to https://en.wikipedia.org/wiki/ASCII
        // '&' ==> 38
        // '%' ==> 37
        // '?' ==> 63
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('%', '?'));
    }
}
