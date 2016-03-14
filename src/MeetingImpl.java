
import java.util.Calendar;
import java.util.Set;
import java.lang.IllegalArgumentException;

/**
 * A class to represent meetings
 *
 * @author stevenjenkins SJENKI05
 *
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */
public abstract class MeetingImpl implements Meeting{

    private int meetingId;
    private Calendar meetingDate;
    private Set<Contact> meetingContacts;

    public MeetingImpl(int Id, Calendar Date, Set<Contact> contacts) {
        if (Id == 0) {
            throw new IllegalArgumentException("Id should not be 0");
        }
        if (Id < 0) {
            throw new IllegalArgumentException("Id should be greater than 0");
        }
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Contacts in empty");
        }
        if (Date == null) {
            throw new NullPointerException("Date must not be null");
        }
        if (contacts == null) {
            throw new NullPointerException("Date must not be null");
        }
        meetingId = Id;
        meetingDate = Date;
        meetingContacts = contacts;
    }

    /**
     * Returns the id of the meeting.
     *
     * @return the id of the meeting.
     */
    public int getId() {
        return this.meetingId;
    }

    /**
     * Return the date of the meeting.
     *
     * @return the date of the meeting.
     */
    public Calendar getDate() {
        return this.meetingDate;
    }

    /**
     * Return the details of people that attended the meeting.
     *
     * The list contains a minimum of one contact (if there were
     ￼￼￼ * just two people: the user and the contact) and may contain an * arbitrary number of them.
     *
     * @return the details of people that attended the meeting.
     */
    public Set<Contact> getContacts() {
        return this.meetingContacts;
    }

}
