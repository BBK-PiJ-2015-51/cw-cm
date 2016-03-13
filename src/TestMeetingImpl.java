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
    Contact n = new ContactImpl(1,"Adam", "late");
    String s = n.getName();
    System.out.println("hello");

    //newContacts2.add(new ContactImpl(1,"Adam", "late"));
    //newContacts.add(new ContactImpl(2,"Brian", "early");
    //newContacts.add(new ContactImpl(3,"Carl", "on time"));
    //newContacts.add(new ContactImpl(4,"Dave", "didn't turn up"));


    //test for constructor

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorIdZero() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Meeting newM = new ConcreteMeetingImpl(0,cal,newContacts);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorContactsEmpty() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Meeting newM = new ConcreteMeetingImpl(0,cal,newContacts);
    }


}
