/**
 * Created by stevenjenkins on 13/03/2016.
 */

import java.util.Calendar;
import java.util.Set;

public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting{

    FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id,date,contacts);
    }
}
