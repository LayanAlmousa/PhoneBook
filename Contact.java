package Phonebook;
import java.util.Date;

/*
Contact: This class will represent a single contact in the phonebook. It should have fields for the 
contact’s name, phone number, email address, address, birthday, and notes. */

public class Contact implements Comparable<Contact> {
	String name;
	String phoneNumber;
	String emailAddress;
	String address;
	Date birthday; 
	String notes;
	LinkedList<Event> events ; 

	public Contact() {
		this.name = "";
		this.phoneNumber = "";
		this.emailAddress = "";
		this.address = "";
		this.birthday = null;
		this.notes = "";
		events = new LinkedList<Event>();
	}

	public Contact(String name, String phoneNumber, String emailAddress, String address, String birthday, String notes) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.address = address;
		this.birthday = new Date(birthday);
		this.notes = notes;
		events = new LinkedList<Event>();
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
