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
 * A class to manage your contacts and meetings.
 *
 * @author stevenjenkins SJENKI05
 *
 */
public class ContactManagerImpl implements ContactManager {

    private Set<Contact> allContacts;
    private int contactId;
    private int meetingId;
    private List<PastMeeting> pastMeetings;
    private List<FutureMeeting> futureMeetings;
    Calendar today = Calendar.getInstance();
    private String filename = "SavedData/ContactManager.ser";

    /**
     * Constructor for Contact Manager
     * Initialises fields, and then reads data if file exists
     */
    public ContactManagerImpl() {
        //initialise all fields
        allContacts = new HashSet<Contact>();
        contactId = 1;
        meetingId = 1;
        pastMeetings =  new ArrayList<PastMeeting>();
        futureMeetings = new ArrayList<FutureMeeting>();
        today = Calendar.getInstance();
        //check if saved file exists and assign values
        File file = new File("SavedData/");
        if (file.exists()) {
            //flushReadMeetingId();
            //flushReadContactId();
            //flushReadContactManager();
            System.out.println("Loading Data From File!");
        }
    }

    /**
     * Used to close reader
     */
    private void closeReader(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add a new meeting to be held in the future. *
     * An ID is returned when the meeting is put into the system. This
     * ID must be positive and non-zero. *
     * @param contacts a list of contacts that will participate in the meeting
     * @param date the date on which the meeting will take place
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time
     * in the past, of if any contact is unknown / non-existent.
     * @throws NullPointerException if the meeting or the date are null
     */
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

    /**
     * Returns the PAST meeting with the requested ID, or null if it there is none. *
     * The meeting must have happened at a past date. *
     * @param id the ID for the meeting
    ￼￼￼* @return the meeting with the requested ID, or null if it there is none.
     * @throws IllegalStateException if there is a meeting with that ID happening in the future
     */
    public PastMeeting getPastMeeting(int id) {

        PastMeeting result = null;
        if (pastMeetings == null) {
            return result;
        }
        //loop through past meetings to match Id
        for(int i = 0; i < pastMeetings.size(); i++) {
            if (pastMeetings.get(i).getId() == id) {
                result = pastMeetings.get(i);
            }
        }
        return result;
    }

    /**
     * Returns the FUTURE meeting with the requested ID, or null if there is none. *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     * @throws IllegalArgumentException if there is a meeting with that ID happening in the past
     */
    public FutureMeeting getFutureMeeting(int id) {
        //go through list of past meetings
        if (pastMeetings != null && getPastMeeting(id)!= null) {
            if (getPastMeeting(id).getId() == id) {
                throw new IllegalArgumentException("Should not be date in the past");
            }
        }
        //go through list of future meetings
        FutureMeeting result = null;
        for(int i = 0; i < futureMeetings.size(); i++) {
            if (futureMeetings.get(i).getId() == id) {
                result = futureMeetings.get(i);
            }
        }
        return result;
    }

    /**
     * Returns the meeting with the requested ID, or null if it there is none. *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    public Meeting getMeeting(int id) {
        Meeting result = null;
        //go through list of past meetings
        if (pastMeetings != null) {
            for (int i = 0; i < pastMeetings.size(); i++) {
                if (pastMeetings.get(i).getId() == id) {
                    result = pastMeetings.get(i);
                }
            }
        }
        //go through list of future meetings
        if (futureMeetings != null) {
            for (int i = 0; i < futureMeetings.size(); i++) {
                if (futureMeetings.get(i).getId() == id) {
                    result = futureMeetings.get(i);
                }
            }
        }
        return result;
    }

    /**
     * Returns the list of future meetings scheduled with this contact. *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any * duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException if the contact is null
     */
    public List<Meeting> getFutureMeetingList(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Contact must not be null");
        }
        List<Meeting> result = new ArrayList<Meeting>();
        //go through future meetings
        for (int i = 0; i < futureMeetings.size(); i++) {
            Set<Contact> tempMeetingContacts = futureMeetings.get(i).getContacts();
            //then go through each contact in future meeting
            for(Contact tempContact: tempMeetingContacts) {
                if (tempContact.getId() == contact.getId()) {
                    result.add(futureMeetings.get(i));
                }
            }
        }
        return result;
    }

    /**
     * Returns the list of meetings that are scheduled for, or that took
     * place on, the specified date *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any * duplicates.
     *
     * @param date the date
     * @return the list of meetings
     * @throws NullPointerException if the date are null
     */
    public List<Meeting> getMeetingListOn(Calendar date) {
        List<Meeting> result = new ArrayList<Meeting>();
        if (date == null) {
            throw new NullPointerException("Date Should not be null");
        }
        //go through pastMeetings
        for (int i = 0; i < pastMeetings.size(); i++) {
            if (pastMeetings.get(i).getDate().compareTo(date) == 0) {
                result.add(pastMeetings.get(i));
            }
        }
        //then go through future meetings
        for (int i = 0; i < futureMeetings.size(); i++) {
            if (futureMeetings.get(i).getDate().compareTo(date) == 0) {
                result.add(futureMeetings.get(i));
            }
        }
        return result;
    }

    /**
     * Returns the list of past meetings in which this contact has participated.
     *
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any * duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException if the contact is null
     */
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        if (contact == null) {
            throw new NullPointerException("Contact must not be null");
        }
        List<PastMeeting> result = new ArrayList<PastMeeting>();
        //go through list of past meetings
        for (int i = 0; i < pastMeetings.size(); i++) {
            Set<Contact> tempMeetingContacts = pastMeetings.get(i).getContacts();
            //go through each contact in past meeting
            for(Contact tempContact: tempMeetingContacts) {
                if (tempContact.getId() == contact.getId()) {
                    result.add(pastMeetings.get(i));
                }
            }

        }

        return result;
    }

    /**
     * Create a new record for a meeting that took place in the past.
     * @param contacts a list of participants
     * @param date the date on which the meeting took place
     * @param text messages to be added about the meeting.
     * @throws IllegalArgumentException if the list of contacts is empty, or any of the contacts does not exist
     * @throws NullPointerException if any of the
     */
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

    /**
     * Add notes to a meeting.
     * This method is used when a future meeting takes place, and is
     * then converted to a past meeting (with notes) and returned. *
     * It can be also used to add notes to a past meeting at a later date. *
     * @param id the ID of the meeting
     * @param text messages to be added about the meeting.
     * @throws IllegalArgumentException if the meeting does not exist
     * @throws IllegalStateException if the meeting is set for a date in the future
     * @throws NullPointerException if the notes are null
     */
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
            //if date set for future
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

    /**
     * Create a new contact with the specified name and notes.
     * @param name the name of the contact.
     * @param notes notes to be added about the contact.
     * @return the ID for the new contact
     * @throws IllegalArgumentException if the name or the notes are empty strings
     * @throws NullPointerException if the name or the notes are null
     */
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

    /**
     * Returns a list with the contacts whose name contains that string. *
     * If the string is the empty string, this methods returns the set
     * that contains all current contacts. *
     * @param name the string to search for
     * @return a list with the contacts whose name contains that string.
     * @throws NullPointerException if the parameter is null
     */
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

    /**
     *arguments is null
     *Calendar date, String text);
     * Returns a list containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     * @param ids an arbitrary number of contact IDs
     * @return a list containing the contacts that correspond to the IDs.
     * @throws IllegalArgumentException if no IDs are provided or if
     *
     */
    public Set<Contact> getContacts(int... ids) {
        if (ids.length == 0) {
            throw new IllegalArgumentException("Ids should not be null");
        }
        Set<Contact> result = new HashSet<Contact>();
        boolean idExists;
        //go through parameter ids
        for(int i = 0; i < ids.length; i++) {
            idExists = false;
            //check against contacts
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

    /**
     * Save all data to disk. *
     * This method must be executed when the program is
     * closed and when/if the user requests it.
     **/
    public void flush() {
        File newFile = new File("SavedData");
        //check if new file exists, create if needed
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        //write all data to files
        flushContactManager();
        flushContactId();
        flushMeetingId();
    }

    /**
     * writes Meetings and Contacts to .ser file
     *
     */
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

    /**
     * writes contact ID to .txt file
     *
     */
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

    /**
     * writes MeetingsIDs to .txt file
     *
     */
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

    /**
     * reads in and sets contact id from .txt
     *
     */
    public void flushReadContactId() {
        File file = new File("SavedData/contactId.txt");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                contactId = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file + " does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeReader(in);
        }
    }

    /**
     * reads in and sets meeting id from .txt
     *
     */
    public void flushReadMeetingId() {
        File file = new File("SavedData/meetingId.txt");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                meetingId = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file + " does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            closeReader(in);
        }
    }

    /**
     * reads in and sets Meetings and Contacts from .ser file
     *
     */
    public void flushReadContactManager() {
        File newFile = new File("SavedData/");
        if (newFile.exists()) {
            try {
                FileInputStream file = new FileInputStream(filename);
                ObjectInputStream input = new ObjectInputStream (file);
                futureMeetings = (List<FutureMeeting>)input.readObject();
                pastMeetings = (List<PastMeeting>)input.readObject();
                allContacts = (Set<Contact>)input.readObject();
                input.close();
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
