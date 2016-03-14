import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

/**
 * A meeting to be held in the future
 *
 * @author stevenjenkins SJENKI05
 *
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting, Serializable{

    /**
     * A constructor for FutureMeetingImpl
     * @param id
     * @param date
     * @param contacts
     * */
    FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id,date,contacts);
    }
}
