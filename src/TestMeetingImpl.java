/**
 * Created by stevenjenkins on 12/03/2016.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class TestMeetingImpl {

    Set<Contact> newContacts = new HashSet<Contact>();

    //test for constructor

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdZero() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        newContacts.add(new ContactImpl(0,"Adam", "late"));
        Meeting newM = new ConcreteMeetingImpl(2,cal,newContacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdBelowZero() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        newContacts.add(new ContactImpl(-6,"Adam", "late"));
        Meeting newM = new ConcreteMeetingImpl(2,cal,newContacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorContactsEmpty() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Meeting newM = new ConcreteMeetingImpl(0,cal,newContacts);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullDate() {
        Calendar cal = null;
        newContacts.add(new ContactImpl(3,"Adam", "late"));
        Meeting newM = new ConcreteMeetingImpl(3,cal,newContacts);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullContacts() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        newContacts = null;
        Meeting newM = new ConcreteMeetingImpl(3,cal,newContacts);
    }

    //Testing methods

    @Test
    public void testGetId() {
        int input = 5;
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        newContacts.add(new ContactImpl(3, "Adam", "late"));
        Meeting newM = new ConcreteMeetingImpl(input, cal, newContacts);
        int output = newM.getId();
        assertEquals(input, output);
    }

    @Test
    public void testDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2016, 3, 21);
        Calendar input = cal;
        newContacts.add(new ContactImpl(3, "Adam", "late"));
        Meeting newM = new ConcreteMeetingImpl(5, cal, newContacts);
        Calendar output = newM.getDate();
        assertEquals(input, output);
    }

    @Test
    public void testContacts() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        newContacts.add(new ContactImpl(3, "Adam", "late"));
        newContacts.add(new ContactImpl(4, "Bob", "late"));
        Set<Contact> newContacts2 = new HashSet<Contact>();
        newContacts2.add(new ContactImpl(3, "Adam", "late"));
        Set<Contact> input = newContacts;
        Meeting newM = new ConcreteMeetingImpl(5, cal, newContacts);
        Set<Contact> output = newM.getContacts();
        assertEquals(input, output);
    }




}
