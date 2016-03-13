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
}
