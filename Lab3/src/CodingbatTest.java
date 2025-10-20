import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodingbatTest {

    @org.junit.Before
    public void setUp() throws Exception {
        Codingbat cb= new Codingbat();
        assertEquals(true ,cb.nearHundred(93));
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void nearHundred() {
    }

    @Test
    public void main() {
    }
}