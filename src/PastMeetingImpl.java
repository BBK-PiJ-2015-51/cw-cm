/**
 * Created by stevenjenkins on 13/03/2016.
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
