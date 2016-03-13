import org.junit.Test;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by stevenjenkins on 13/03/2016.
 */
public class TestFutureMeeting {

    //test constructor

    @Test(expected = NullPointerException.class)
    public void testConstructorContacts() {
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        Set<Contact> newContacts = new HashSet<Contact>();
        newContacts.add(new ContactImpl(3,"Adam", "late"));
        Set<Contact> newContacts2 = null;
        String notes = "good meeting";
        String notes2 = null;
        FutureMeeting newM = new FutureMeetingImpl(2,cal,newContacts2);
    }

}
