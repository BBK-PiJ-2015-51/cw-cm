import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * Created by stevenjenkins on 13/03/2016.
 */
public class TestContactManager {

    ContactManager newCM = new ContactManagerImpl();

    //test add new contact

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewContactNameEmpty() {
        String name = "";
        String notes = "Late";
        newCM.addNewContact(name,notes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewContactNotesEmpty() {
        String name = "Bob";
        String notes = "";
        newCM.addNewContact(name,notes);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameNull() {
        String name = null;
        String notes = "Late";
        newCM.addNewContact(name,notes);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesNull() {
        String name = "Bob";
        String notes = null;
        newCM.addNewContact(name,notes);
    }

    @Test
    public void testAddNewContact() {
        newCM.addNewContact("Adam","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Carl","Early");
        newCM.addNewContact("Dave","Early");
        newCM.addNewContact("Edward","Early");
        int input = 6;
        int output = newCM.addNewContact("Fred","Early");
        assertEquals(input, output);
    }

    //test getContacts

    @Test(expected = NullPointerException.class)
    public void testGetContactsNamesNull() {
        newCM.addNewContact("Adam", "Early");
        newCM.addNewContact("Bob", "Early");
        newCM.addNewContact("Carl", "Early");
        newCM.addNewContact("Dave", "Early");
        newCM.addNewContact("Edward", "Early");
        String name = null;
        newCM.getContacts(name);
    }

    
}
