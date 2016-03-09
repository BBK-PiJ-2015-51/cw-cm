/**
 * Created by stevenjenkins SJENKI05 on 09/03/2016.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;

public class TestContact {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdZero() {
        Contact newC = new ContactImpl(0,"name","notes");
    }



}
