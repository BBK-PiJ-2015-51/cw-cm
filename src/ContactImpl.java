
import java.io.Serializable;
import java.lang.IllegalArgumentException;

/**
 * A contact is a person we are making business with or may do in the future.
 *
 * Contacts have an ID (unique, a non-zero positive integer),
 * a name (not necessarily unique), and notes that the user
 * may want to save about them.
 *
 *@author stevenjenkins SJENKI05
 *
 */
public class ContactImpl implements Contact, Serializable {

    private int contactId;
    private String contactName;
    private String contactNotes;

    /**
     * Constructor for 2 params
     *
     * @param id
     * @param name
     *
     */
    public ContactImpl(int id, String name) {
        this(id,name,"");
    }

    /**
     * Constructor for 3 params
     *
     * @param id
     * @param name
     * @param note
     *
     */
    public ContactImpl(int id, String name, String note) {
        if (id == 0) {
            throw new IllegalArgumentException("ID should not be 0");
        }

        if (id < 0) {
            throw new IllegalArgumentException("ID should not be negative");
        }

        if (name == null || note == null) {
            throw new NullPointerException("ID should not be null");
        }

        this.contactId = id;
        this.contactName = name;
        this.contactNotes = note;
    }

    /**
     * Returns the ID of the contact.
     *
     * @return the ID of the contact.
     *
     */
    public int getId() {
        return this.contactId;
    }

    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact.
     */
    public String getName() {
        return this.contactName;
    }

    /**
     * Returns our notes about the contact, if any.
     *
     * If we have not written anything about the contact, the empty
     * string is returned.
     *
     * @return a string with notes about the contact, maybe empty.
     */
    public String getNotes() {
        return this.contactNotes;
    }

    /**
     * Add notes about the contact.
     *
     * @param note the notes to be added
     */
    public void addNotes(String note) {
        this.contactNotes = contactNotes  + note;
    }
}
