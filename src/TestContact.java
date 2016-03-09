/**
 * Created by stevenjenkins SJENKI05 on 09/03/2016.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;

public class TestContact {

    //test for the constructor

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdZero() {
        Contact newC = new ContactImpl(0,"name","notes");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdNegative() {
        Contact newC = new ContactImpl(-6,"name", "notes");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNameNull() {
        String name = null;
        Contact newC = new ContactImpl(5,name, "notes");
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNotesNull() {
        String notes = null;
        Contact newC = new ContactImpl(5,"name", notes);
    }

    @Test
    public void testGetId() {
        int input = 5;
        Contact newC = new ContactImpl(input,"name","notes");
        int output = newC.getId();
        assertEquals(input,output);

    }

    @Test
    public void testGetName() {
        String input = "Bob";
        Contact newC = new ContactImpl(10,input,"notes");
        String output = newC.getName();
        assertEquals(input,output);

    }

    @Test
    public void testGetNotes() {
        String input = "late";
        Contact newC = new ContactImpl(10,"Carl",input);
        String output = newC.getNotes();
        assertEquals(input,output);

    }

    @Test
    public void testGetNotesEmpty() {
        String input = "";
        Contact newC = new ContactImpl(10,"Carl");
        String output = newC.getNotes();
        assertEquals(input,output);

    }

    @Test
    public void testAddNotesWhenEmpty() {
        String input = "late again";
        Contact newC = new ContactImpl(10,"Carl");
        newC.addNotes("late again");
        String output = newC.getNotes();
        assertEquals(input,output);

    }

    @Test
    public void testAddAdditionalNotes() {
        String firstInput = "late again";
        Contact newC = new ContactImpl(10,"Carl",firstInput);
        newC.addNotes("travel problems");
        String input = "late againtravel problems";
        String output = newC.getNotes();
        assertEquals(input,output);

    }
}
