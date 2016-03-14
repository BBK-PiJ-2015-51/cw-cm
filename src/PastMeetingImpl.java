import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
/**
 * A meeting that was held in the past.
 *
 * @author stevenjenkins SJENKI05
 *
 * It includes your notes about what happened and what was agreed.
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting, Serializable {

    private String meetingNotes;

    /**
     * Constructor for Past MeetingImpl
     *
     * @param id
     * @param date
     * @param contacts
     * @param notes
     *
     */
    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) {
        super(id, date,contacts);
        if (notes == null) {
            throw new NullPointerException("Notes should not be null");
        }
        meetingNotes = notes;
    }

    /**
     * Returns the notes from the meeting.
     *
     * If there are no notes, the empty string is returned.
     *
     * @return the notes from the meeting.
     */
    public String getNotes() {
        return meetingNotes;
    }

    /**
     * Sets the meeting notes
     */
    public void setNotes(String notes) {
        meetingNotes = meetingNotes + notes;
    }
}
