/**
 * Created by stevenjenkins on 13/03/2016.
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.lang.IllegalArgumentException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class TestPastMeeting {

    //test comnstructor

    @Test(expected = NullPointerException.class)
    public void testConstructorNotesNull() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Set<Contact> newContacts = new HashSet<Contact>();
        newContacts.add(new ContactImpl(3,"Adam", "late"));
        String notes = "good meeting";
        String notes2 = null;
        PastMeeting newM = new PastMeetingImpl(2,cal,newContacts,notes2);
    }

    //Testing methods

    @Test
    public void testGetNotes() {
        String input = "Bad Meeting";
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Set<Contact> newContacts = new HashSet<Contact>();
        newContacts.add(new ContactImpl(3, "Adam", "late"));
        PastMeeting newMeeting = new PastMeetingImpl(3, cal, newContacts, input);
        String output = newMeeting.getNotes();
        assertEquals(input, output);
    }

    @Test
    public void testGetNotesNoNotes() {
        String input = "";
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Set<Contact> newContacts = new HashSet<Contact>();
        newContacts.add(new ContactImpl(3, "Adam", "late"));
        PastMeeting newMeeting = new PastMeetingImpl(3, cal, newContacts, input);
        String output = newMeeting.getNotes();
        assertEquals(input, output);
    }

}
