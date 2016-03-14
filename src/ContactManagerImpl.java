import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.NotSerializableException;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by stevenjenkins on 13/03/2016.
 */

public class ContactManagerImpl implements ContactManager {

    private Set<Contact> allContacts;
    private int contactId;
    private int meetingId;
    private List<PastMeeting> pastMeetings;
    private List<FutureMeeting> futureMeetings;
    Calendar today = Calendar.getInstance();
    private String filename = "SavedData/ContactManager.ser";

    public ContactManagerImpl() {
            allContacts = null;
            contactId = 1;
            meetingId = 1;
            pastMeetings = null;
            futureMeetings = null;
            today = Calendar.getInstance();
    }

    private void closeReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

        PastMeeting result = null;
        if (pastMeetings == null) {
            return result;
        }

        for(int i = 0; i < pastMeetings.size(); i++) {
            if (pastMeetings.get(i).getId() == id) {
                result = pastMeetings.get(i);
            }
        }
        return result;
    }

    public FutureMeeting getFutureMeeting(int id) {
        if (pastMeetings != null && getPastMeeting(id)!= null) {
            if (getPastMeeting(id).getId() == id) {
                throw new IllegalArgumentException("Should not be date in the past");
            }
        }
        FutureMeeting result = null;
        for(int i = 0; i < futureMeetings.size(); i++) {
            if (futureMeetings.get(i).getId() == id) {
                result = futureMeetings.get(i);
            }
        }
        return result;
    }

    public Meeting getMeeting(int id) {
        Meeting result = null;
        if (pastMeetings != null) {
            for (int i = 0; i < pastMeetings.size(); i++) {
                if (pastMeetings.get(i).getId() == id) {
                    result = pastMeetings.get(i);
                }
            }
        }
        if (futureMeetings != null) {
            for (int i = 0; i < futureMeetings.size(); i++) {
                if (futureMeetings.get(i).getId() == id) {
                    result = futureMeetings.get(i);
                }
            }
        }
        return result;
    }

    public List<Meeting> getFutureMeetingList(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Contact must not be null");
        }
        List<Meeting> result = new ArrayList<Meeting>();
        for (int i = 0; i < futureMeetings.size(); i++) {
            Set<Contact> tempMeetingContacts = futureMeetings.get(i).getContacts();
            for(Contact tempContact: tempMeetingContacts) {
                if (tempContact.getId() == contact.getId()) {
                    result.add(futureMeetings.get(i));
                }
            }
        }
        return result;
    }

    public List<Meeting> getMeetingListOn(Calendar date) {
        List<Meeting> result = new ArrayList<Meeting>();
        if (date == null) {
            throw new NullPointerException("Date Should not be null");
        }
        for (int i = 0; i < pastMeetings.size(); i++) {
            if (pastMeetings.get(i).getDate().compareTo(date) == 0) {
                result.add(pastMeetings.get(i));
            }
        }
        for (int i = 0; i < futureMeetings.size(); i++) {
            if (futureMeetings.get(i).getDate().compareTo(date) == 0) {
                result.add(futureMeetings.get(i));
            }
        }
        return result;
    }

    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Contact must not be null");
        }
        List<PastMeeting> result = new ArrayList<PastMeeting>();
        for (int i = 0; i < pastMeetings.size(); i++) {
            Set<Contact> tempMeetingContacts = pastMeetings.get(i).getContacts();
            for(Contact tempContact: tempMeetingContacts) {
                if (tempContact.getId() == contact.getId()) {
                    result.add(pastMeetings.get(i));
                }
            }

        }

        return result;
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
        //check if meeting exists
        Meeting meeting = getMeeting(id);
        if(meeting==null) {
            throw new IllegalArgumentException("Meeting does not exist");
        }
        //if its an existing past meeting just add notes
        PastMeeting pastMeeting = getPastMeeting(id);
        if(pastMeeting != null) {
            PastMeetingImpl pM = ((PastMeetingImpl) pastMeeting);
            pM.setNotes(text);
            //if this is a future meeting
        } else {
            FutureMeeting fM = getFutureMeeting(id);
            //datesetforfuture
            if(today.compareTo(fM.getDate()) < 0) {
                throw new IllegalStateException("Date is in the future");
            } else {
                //Add future meeting to Past Meetings
                PastMeeting newPM = new PastMeetingImpl(id, fM.getDate(),fM.getContacts(), text);
                pastMeetings.add(newPM);
                pastMeeting = newPM;
                //remove item from ArrayList
                for(int i = 0; i < futureMeetings.size(); i++) {
                    if (futureMeetings.get(i).getId() == id) {
                        futureMeetings.remove(i);
                    }
                }
            }
        }
        return pastMeeting;
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
        File newFile = new File("SavedData");
        newFile.mkdir();
        flushContactManager();
        flushContactId();
        flushMeetingId();

    }

    public void flushContactManager() {
        try {
            FileOutputStream fileOut = new FileOutputStream("SavedData/ContactManager.ser");
            // FileOutputStream buffer = new BufferedOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(futureMeetings);
            out.writeObject(pastMeetings);
            out.writeObject(allContacts);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename);
        }  catch (NotSerializableException e) {
            e.printStackTrace();
        } catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void flushContactId() {
        File file = new File("SavedData/contactId.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            out.write(new Integer(contactId).toString());
        } catch (FileNotFoundException ex) {
         System.out.println("Cannot write to file " + file + ".");
        } finally {
            out.close();
        }

    }

    public void flushMeetingId() {
        File file = new File("SavedData/meetingId.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            out.write(new Integer(meetingId).toString());
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot write to file " + file + ".");
        } finally {
            out.close();
        }

    }

}
