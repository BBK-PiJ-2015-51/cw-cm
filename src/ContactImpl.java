import com.sun.javaws.exceptions.InvalidArgumentException;

/**
 * Created by stevenjenkins on 09/03/2016.
 */

import java.lang.IllegalArgumentException;

public class ContactImpl implements Contact {

    private int contactId;
    private String contactName;
    private String contactNotes;

    public ContactImpl(int id, String name) {
        this(id,name,"");
    }

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

    public int getId() {
        return this.contactId;
    }

    public String getName() {
        return this.contactName;
    }

    public String getNotes() {
        return this.contactNotes;
    }

    public void addNotes(String note) {
        this.contactNotes = contactNotes + note;
    }
}
