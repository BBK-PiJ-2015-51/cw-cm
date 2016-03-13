/**
 * Created by stevenjenkins on 13/03/2016.
 */

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String meetingNotes;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) {
        super(id, date,contacts);
        if (notes == null) {
            throw new NullPointerException("Notes should not be null");
        }
        meetingNotes = notes;
    }

    public String getNotes() {
        return meetingNotes;
    }

    public void setNotes(String notes) {
        meetingNotes = meetingNotes + notes;
    }
}
