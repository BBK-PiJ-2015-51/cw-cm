import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Created by stevenjenkins on 13/03/2016.
 */

public class ContactManagerImpl implements ContactManager {

    private Set<Contact> allContacts;
    private int contactId = 1;
    public ContactManagerImpl() {

    }

    public int addFutureMeeting(Set<Contact> contacts, Calendar date){
        return 0;
    }

    public PastMeeting getPastMeeting(int id) {
        return null;
    }

    public FutureMeeting getFutureMeeting(int id) {
        return null;
    }

    public Meeting getMeeting(int id) {
        return null;
    }

    public List<Meeting> getFutureMeetingList(Contact contact) {
        return null;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        return null;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        return null;
    }


    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {

    }

    public PastMeeting addMeetingNotes(int id, String text) {
        return null;
    }

    public int addNewContact(String name, String notes) {
        if (allContacts == null) {
            allContacts = new HashSet<Contact>();
        }

        if (name.equals("")) {
            throw new IllegalArgumentException("Name should not be empty");
        }

        if (notes.equals("")) {
            throw new IllegalArgumentException("Notes should not be empty");
        }

        if (name == null) {
            throw new NullPointerException("Name should not be null");
        }

        if (notes == null) {
            throw new NullPointerException("Notes should not be null");
        }
        Contact newC = new ContactImpl(contactId,name,notes);
        allContacts.add(newC);
        contactId++;
        return newC.getId();
    }

    public Set<Contact> getContacts(String name) {
       if (name == null) {
           throw new NullPointerException("Name should not be null");
       }
        Set<Contact> result = new HashSet<Contact>();
        for(Contact c :allContacts) {
            Contact tempContact = c;
                if (c.getName().contains(name)) {
                    result.add(tempContact);
                }
            }
        return result;
    }

    public Set<Contact> getContacts(int... ids) {
        if (ids.length == 0) {
            throw new IllegalArgumentException("Ids should not be null");
        }
        
        Set<Contact> result = new HashSet<Contact>();
        boolean idExists = true;
        for(int i = 0; i < ids.length; i++) {
            for (Contact c : allContacts) {
                if (c.getId() == ids[i]) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public void flush() {

    }


}
