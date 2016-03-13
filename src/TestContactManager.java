import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by stevenjenkins on 13/03/2016.
 */
public class TestContactManager {

    ContactManager newCM = new ContactManagerImpl();

    //create empty contact list
    //create completed contact list
    //create date
    //create String null, empty and value


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
        System.out.println("testing single");
        for(Contact c: result) {
            System.out.println(c.getName());
        }
        int output = result.size();
        assertEquals(input, output);
    }

    //test getContacts multiple ids

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsByIDNull() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        newCM.getContacts();
    }



    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsByIDNoId() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        newCM.getContacts(4,8);
    }

    //test add new past meeting

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingContactIsEmpty() {
        Set<Contact> newC = new HashSet<Contact>();
        //newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        String s = "good";
        newCM.addNewPastMeeting(newC,cal, s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingContactNotExists() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        String s = "good";
        newCM.addNewPastMeeting(newCM.getContacts(1,9),cal,s);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingContactIsNull() {
        Set<Contact> newC = null;
        //newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        String s = "good";
        newCM.addNewPastMeeting(newC,cal, s);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingDateIsNull() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar cal = null;
        String s = "good";
        newCM.addNewPastMeeting(newC,cal, s);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNotesIsNull() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 3, 20);
        String s = null;
        newCM.addNewPastMeeting(newC,cal, null);
    }

    // test get past meeting

    @Test
    public void testGetPastMeeting() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2, "Adam Jones", "Early"));
        Calendar calPast = Calendar.getInstance();
        calPast.set(2016, 3, 20);
        Calendar calPast2 = Calendar.getInstance();
        calPast2.set(2016, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2016, 3, 20);
        String notes1 = "first";
        String notes2 = "second";
        newCM.addNewPastMeeting(newC, calPast, notes1);
        newCM.addNewPastMeeting(newC, calPast2, notes2);
        String input = notes1;
        String output = newCM.getPastMeeting(1).getNotes();
        assertEquals(input, output);
    }

    @Test
    public void testGetPastMeetingNoMeeting() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar calPast = Calendar.getInstance();
        calPast.set(2016, 3, 20);
        Calendar calPast2 = Calendar.getInstance();
        calPast2.set(2016, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2016, 3, 20);
        String notes1 = "first";
        String notes2 = "second";
        newCM.addNewPastMeeting(newC,calPast, notes1);
        newCM.addNewPastMeeting(newC,calPast2, notes2);
        String input = null;
        PastMeeting output = newCM.getPastMeeting(9);
        assertEquals(input, output);
    }

   /* @Test
    public void testGetPastMeetingIdIsFutureMeeting() {
       int input = 1;
        int output = 2;
        assertEquals(input, output);
    }*/


    //test add future meeting

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingPastMeeting() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar calPast = Calendar.getInstance();
        calPast.set(2014, 3, 10);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        newCM.addFutureMeeting(newC,calPast);
    }

    @Test(expected = NullPointerException.class)
    public void testAddFutureMeetingContactNull() {
        Set<Contact> newC = null;
        Calendar calPast = Calendar.getInstance();
        calPast.set(2014, 3, 10);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        newCM.addFutureMeeting(newC,calFuture);
    }
    @Test(expected = NullPointerException.class)
    public void testAddFutureMeetingDateNull() {
        Set<Contact> newC = new HashSet<Contact>();
        newC.add(new ContactImpl(2,"Adam Jones","Early"));
        Calendar calFuture = null;
        newCM.addFutureMeeting(newC,calFuture);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingContactNotExist() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        newCM.addFutureMeeting(newCM.getContacts(1,2,9),calFuture);
    }

    //test get future meeting

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingPastMeeting() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.getFutureMeeting(1);

    }

    @Test
    public void testGetFutureMeetingId() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(2,3), calFuture);
        int input = 1;
        int output = newCM.getFutureMeeting(input).getId();
        assertEquals(input, output);
    }

    @Test
    public void testGetFutureMeetingIdNotExists() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(2,3), calFuture);
        newCM.getFutureMeeting(5);
        FutureMeeting input = null;
        FutureMeeting output = newCM.getFutureMeeting(10);
        assertEquals(input,output);
    }

    // test get future meeting list on

    @Test(expected = NullPointerException.class)
    public void testGetMeetingListOnDateNull() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);

        Calendar cal = null;
        newCM.getMeetingListOn(cal);
    }

    //brings back correct list (for past list)
    @Test
    public void testGetMeetingListOnPastList() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        int input = 2;
        int output = newCM.getMeetingListOn(calPast).size();
        assertEquals(input,output);
    }

    @Test
    //brings back correct list (for future list)
    public void testGetMeetingListOnFutureList() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5), calFuture);
        int input = 3;
        int output = newCM.getMeetingListOn(calFuture).size();
        assertEquals(input,output);
    }

    @Test
    public void testGetMeetingListOnNull() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5), calFuture);
        Calendar calNone = Calendar.getInstance();
        calNone.set(2019, 3, 20);
        int input = 0;
        int output = newCM.getMeetingListOn(calNone).size();
        assertEquals(input,output);
    }

    // test getPastMeetingListfor

    @Test(expected = NullPointerException.class)
    public void testGetPastMeetingListForContactNull(){
        Contact newC = null;
        newCM.getPastMeetingListFor(newC);
    }

    //illegal argument contact does not exits


    @Test
    public void testGetPastMeetingListFor() {
        Contact a = new ContactImpl(1,"Adam Jones","Early");
        Contact b = new ContactImpl(2,"Bob","Early");
        Contact c = new ContactImpl(3,"Bob Tayor","Early");
        Contact d = new ContactImpl(4,"Dave Johnson","Early");
        Contact e = new ContactImpl(5,"Edward Carter","Early");

        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,3,2),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,5),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        int input = 2;
        int output = newCM.getPastMeetingListFor(d).size();
        assertEquals(input,output);
    }

    // test get meeting

    @Test
    public void testGetMeeting() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5), calFuture);
        int input =  3;
        int output = newCM.getMeeting(input).getId();
        assertEquals(input,output);
    }

    @Test
    public void testGetMeetingNull() {
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calPast = Calendar.getInstance();
        calPast.set(2015, 3, 20);
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addNewPastMeeting(newCM.getContacts(1,2,3),calPast,"good meeting" );
        newCM.addNewPastMeeting(newCM.getContacts(1,2,4),calPast,"good meeting" );
        //add new futuremeeting
        newCM.addFutureMeeting(newCM.getContacts(1,2), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,3), calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5), calFuture);
        Meeting input =  null;
        Meeting output = newCM.getMeeting(9);
        assertEquals(input,output);
    }

    // test get future meeting list

    @Test(expected = NullPointerException.class)
    public void testGetFutureMeetingListContactNull(){
        Contact newC = null;
        newCM.getFutureMeetingList(newC);
    }

    //illegal argument contact does not exits

    //check correct meetings occured
    @Test
    public void testGetFutureMeetingList() {
        Contact a = new ContactImpl(1,"Adam Jones","Early");
        Contact b = new ContactImpl(2,"Bob","Early");
        Contact c = new ContactImpl(3,"Bob Tayor","Early");
        Contact d = new ContactImpl(4,"Dave Johnson","Early");
        Contact e = new ContactImpl(5,"Edward Carter","Early");

        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addFutureMeeting(newCM.getContacts(1,3,2),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,4),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,4),calFuture);
        int input = 2;
        int output = newCM.getFutureMeetingList(d).size();
        assertEquals(input,output);
    }

    @Test
    public void testGetFutureMeetingListEmpty() {
        Contact a = new ContactImpl(1,"Adam Jones","Early");
        Contact b = new ContactImpl(2,"Bob","Early");
        Contact c = new ContactImpl(3,"Bob Tayor","Early");
        Contact d = new ContactImpl(4,"Dave Johnson","Early");
        Contact e = new ContactImpl(5,"Edward Carter","Early");
        Contact f = new ContactImpl(6,"Edward Miller","Early");
        newCM.addNewContact("Adam Jones","Early");
        newCM.addNewContact("Bob","Early");
        newCM.addNewContact("Bob Tayor","Early");
        newCM.addNewContact("Dave Johnson","Early");
        newCM.addNewContact("Edward Carter","Early");
        Calendar calFuture = Calendar.getInstance();
        calFuture.set(2017, 3, 20);
        //add newpastmeeting
        newCM.addFutureMeeting(newCM.getContacts(1,3,2),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,4),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,5),calFuture);
        newCM.addFutureMeeting(newCM.getContacts(1,2,4),calFuture);
        int input = 0;
        int output = newCM.getFutureMeetingList(f).size();
        assertEquals(input,output);
    }
}
