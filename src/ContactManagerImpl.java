import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;


/**
 * Created by stevenjenkins on 13/03/2016.
 */

public class ContactManagerImpl implements ContactManager {

    private Set<Contact> allContacts = null;
    private int contactId = 1;
    private int meetingId = 1;
    private List<PastMeeting> pastMeetings = null;
    private List<FutureMeeting> futureMeetings = null;
    Calendar today = Calendar.getInstance();

    public ContactManagerImpl() {

    }

    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        if (futureMeetings ==null) {
            futureMeetings = new ArrayList<FutureMeeting>();
        }
        if (date==null) {
            throw new NullPointerException("Date should not be null");
        }
        if (contacts == null) {
            throw new NullPointerException("Contacts should not be null");
        }
        if (today.compareTo(date) > 0) {
            throw new IllegalArgumentException("Meeting is in the past");
        }
        FutureMeeting newFM = new FutureMeetingImpl(meetingId, date, contacts);
        futureMeetings.add(newFM);
        meetingId++;
        return newFM.getId();
    }

    public PastMeeting getPastMeeting(int id) {
        if (pastMeetings == null) {
            return null;
        }
        PastMeeting result = null;
        for(int i = 0; i < pastMeetings.size(); i++) {
            if (pastMeetings.get(i).getId() == id) {
                result = pastMeetings.get(i);
            }
        }
        return result;
    }

    public FutureMeeting getFutureMeeting(int id) {
        if (pastMeetings != null) {
            if (getPastMeeting(id).getId() == id) {
                throw new IllegalArgumentException("Should not be date in the past");
            }
        }

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
        if (pastMeetings == null) {
            pastMeetings = new ArrayList<PastMeeting>();
        }
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Contact should not be empty");
        }
        if (contacts == null) {
            throw new NullPointerException("Contacts should not be null");
        }
        if (date == null) {
            throw new NullPointerException("date should not be null");
        }
        if (text == null) {
            throw new NullPointerException("text should not be null");
        }
        PastMeeting newPM = new PastMeetingImpl(meetingId, date, contacts, text);
        pastMeetings.add(newPM);
        meetingId++;
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
        boolean idExists;
        for(int i = 0; i < ids.length; i++) {
            idExists = false;
            for (Contact c : allContacts) {
                if (c.getId() == ids[i]) {
                    result.add(c);
                    idExists = true;
                }
            }
            if (!idExists) {
                 throw new IllegalArgumentException("Id does not exist");
            }
        }
        return result;
    }

    public void flush() {

    }


}
