/**
 * Created by stevenjenkins on 12/03/2016.
 */
import java.util.Calendar;
import java.util.Set;
import java.lang.IllegalArgumentException;
import java.util.HashSet;

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

        public int getId() {
            return this.meetingId;
        }

        /*public void setId(int id) {
            meetingId = id;
        }*/


        public Calendar getDate() {
            return this.meetingDate;
        }

        /*public void setDate(Calendar date) {
            meetingDate = date;
        }*/


        public Set<Contact> getContacts() {
            return this.meetingContacts;
        }

        /*public void setContacts(Set<Contact> contacts) {
            meetingContacts = contacts;
        }*/


}
