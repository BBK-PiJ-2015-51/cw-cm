
import java.util.Calendar;
import java.util.Set;

/**
 * A concrete class that instantiates the abstract class MeetingImpl
 * used for testing purposes only
 *
 * @author stevenjenkins  SJENKI05
 *
 */
public class ConcreteMeetingImpl extends MeetingImpl {

    /**
     *constructor for concrete class
     */
    public ConcreteMeetingImpl(int Id, Calendar Date, Set<Contact> contacts) {
        super(Id, Date,contacts);
    }

}
