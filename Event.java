package Phonebook;
import java.util.Date;

/*
Event: This class will represent events or appointment that can be scheduled with a contact. It 
should have fields for the event title, date and time, location, and the contact involved in this 
event.
 */

public class Event implements Comparable<Event> {
	String title;
	Date date;
	String time;
	String location;
	boolean EventType;  // if true then its an EVENT, false its an APPOINTMENT
	LinkedList <String> contactsNames;

	public Event() {
		this.title = "";
		this.date = null;
		this.time = "";
		this.location = "";
		this.EventType = true;
		this.contactsNames = new LinkedList<String> ();
	}

	public Event(String title, String date, String time, String location, boolean t, String contact) {
		this.title = title;
		this.date = new Date(date);
		this.time = time;
		this.location = location;
		this.EventType =t;
		this.contactsNames = new LinkedList<String> ();
		contactsNames.insert(contact);
	}
// first it checks the type if its an event or (If it's an appointment and there are no contacts added to it( there is no conflict)
//if it is true then the new contact is added to the contactsNames list. Otherwise, it prints a message that says "You can't Add an appointment. there is conflict." and return false.
	public boolean addContact (String contact)
	{
		if ((this.EventType == true) || ((this.EventType == false)&&(contactsNames.size == 0)))
			return contactsNames.insert(contact);

		System.out.println("You can't Add an appointment. there is conflict.");
		return false;
	}
//removes a contact from the event's contactsNames list.
//search for the contact name in the list, if it's found then it's deleted and return true else the name is not found and return false
	public boolean removeContact(String contact)
	{
		String name = contactsNames.remove(contact);
		if ( name != null)
			return true; 
		return false;
	}

	@Override
	public String toString() {
		String EventT = (this.EventType == true)? "Event ": "Appoinment ";  // if its not event then its an appointment     
		String str = "\n" + EventT + " title: " + title +
				"\n " + EventT + "  date and time (MM/DD/YYYY HH:MM): " + date + time +
				"\n" + EventT + " location: " + location +
				"\nType: " + EventT +
				"\nContacts names:   \n" + contactsNames.toString();

		return str;
	}


	@Override
	public int compareTo(Event obj) {
		try {
			return (this.title.compareToIgnoreCase(obj.title));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public boolean compareToSameEvent(Event obj) {
		try {
			return ((this.title.compareToIgnoreCase(obj.title) == 0) && 
					(this.date.compareTo(obj.date) == 0) &&
					(this.time.compareToIgnoreCase(obj.time) == 0) && (this.EventType == obj.EventType));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

}
