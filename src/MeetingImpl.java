/**
 * Created by stevenjenkins on 12/03/2016.
 */
import java.util.Calendar;
import java.util.Set;


public abstract class MeetingImpl implements Meeting{

        private int meetingId;
        private Calendar meetingDate;
        private Set<Contact> meetingContacts;


        public int getId() {
            return this.meetingId;
        }

        public void setId(int id) {
            meetingId = id;
        }


        public Calendar getDate() {
            return this.meetingDate;
        }

        public void setDate(Calendar date) {
            meetingDate = date;
        }


        public Set<Contact> getContacts() {
            return this.meetingContacts;
        }

        public void setContacts(Set<Contact> contacts) {
            meetingContacts = contacts;
        }


}
