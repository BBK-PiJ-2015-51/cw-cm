/**
 * Created by stevenjenkins on 12/03/2016.
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Calendar;
import java.util.Set;
import java.lang.IllegalArgumentException;

public class ConcreteMeetingImpl extends MeetingImpl {

    public ConcreteMeetingImpl(int Id, Calendar Date, Set<Contact> contacts) {

        if (Id == 0) {
            throw new IllegalArgumentException("Id should not be 0");
        }

        if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Contacts in empty");
        }

        setContacts(contacts);
        setId(Id);
        setDate(Date);
    }
}
