import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void testGetContactsContainsName() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(1,"Bob Smith","Early"));
        newC.add(new ContactImpl(2,"Bob Tayor","Early"));
        int input = newC.size();
        Set<Contact> result = newCM.getContacts("Bob");
        for(Contact c: result) {
            System.out.println(c.getName());
        }
        int output = result.size();
        assertEquals(input, output);
    }

    @Test
    public void testGetContactsAllContacts() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        int input = 5;
        Set<Contact> result =  newCM.getContacts("");
        for(Contact c: result) {
            System.out.println(c.getName());
        }
        int output = result.size();
        assertEquals(input, output);
    }

    //test getContacts multiple names
}
