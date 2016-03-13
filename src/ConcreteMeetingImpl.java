/**
 * Created by stevenjenkins on 12/03/2016.
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Calendar;
import java.util.Set;
import java.lang.IllegalArgumentException;
import java.util.HashSet;

public class ConcreteMeetingImpl extends MeetingImpl {

    public ConcreteMeetingImpl(int Id, Calendar Date, Set<Contact> contacts) {
        super(Id, Date,contacts);
    }

}
