package Phonebook;
import java.util.Date;

/*
Contact: This class will represent a single contact in the phonebook. It should have fields for the 
contact’s name, phone number, email address, address, birthday, and notes. */

//implements the Comparable<Contact> interface so it can be compared to other Contact objects.
public class Contact implements Comparable<Contact> {
	String name;
	String phoneNumber;
	String emailAddress;
	String address;
	Date birthday; 
	String notes;
	LinkedList<Event> events ; 
	
//default constructor
	public Contact() {
		this.name = "";
		this.phoneNumber = "";
		this.emailAddress = "";
		this.address = "";
		this.birthday = null;
		this.notes = "";
		events = new LinkedList<Event>();
	}
//constructor
	public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.address = address;
		this.birthday = new Date(birthday);
		this.notes = notes;
		events = new LinkedList<Event>(); //a LinkedList of events associated with the contact
	}

	@Override
	public String toString() {
		return "\nName: " + name +
				"\nPhone Number: " + phoneNumber +
				"\nEmail Address: " + emailAddress +
				"\nAddress: " +  address +
				"\nBirthday: " + birthday +
				"\nNotes: " + notes + 
				"\nEvents : " + events.toString();

	}
//The addEvent method is used to add an event to the contact's events list. 
//It checks if there are any existing events and iterates through them to check for any conflicts with the new event's date and time.
// If there is a conflict, it returns false. Otherwise, it adds the event to the list and returns true.
	public boolean addEvent( Event e)
	{
		
			if (! events.empty())
			{
				events.findFirst();
				for ( int i = 0 ; i < events.size ; i++)
				{
					if ((events.retrieve().date.compareTo(e.date) == 0) 
							&& (events.retrieve().time.compareTo(e.time) == 0)) //to check for conflict
						return false;
				}
			}
			events.insert(e);
			return true;
	}
//removes an event from the contact's events list based on the event title
	public boolean removeEvent(String eventTitle)
	{
		if (events.empty())
			return false;
		Event val = new Event();
		val.title = eventTitle;
		if (events.search(val))
		{
			events.remove(val);
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Contact c) {
		try {
			return (this.name.compareToIgnoreCase(c.name));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}


	public int compareToPhone(String Phone) {
		try {
			return (this.phoneNumber.compareToIgnoreCase(Phone));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public int compareToEmail(String emailAddress) {
		try {
			return (this.emailAddress.compareToIgnoreCase(emailAddress));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public int compareToAddress(String address) {
		try {
			return (this.address.compareToIgnoreCase(address));
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public int compareToBirthday(Date birthday) {
		try {
			return (this.birthday.compareTo(birthday) ) ;
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	public int compareFirstName(String n) {
		try {
			String [] fullName = this.name.split(" "); //to separate the first and last name
			return (fullName[0].trim().compareToIgnoreCase(n) ) ; //the first name
		}
		catch (Exception e)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
