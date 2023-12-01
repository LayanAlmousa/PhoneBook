package Phonebook;
import java.util.Date;
import java.util.Scanner;

/*

This class represents a phonebook application. It includes a field for a linked list data structure that stores contacts,
as well as methods for interacting with the contact list such as adding, searching, and deleting contacts.
Additionally, it provides functionality for scheduling events and appointments with contacts.
 */

public class Phonebook {

	public static Scanner input = new Scanner (System.in);
	public static BST <String, Contact> contacts = new BST <String, Contact>();
	public static LinkedList <Event> events = new LinkedList <Event>();

	public static int menu ()//a user-friendly interface for interacting with the phonebook
	{
		System.out.println("Please choose an option:");
		System.out.println("1. Add a contact");
		System.out.println("2. Search for a contact");
		System.out.println("3. Delete a contact");
		System.out.println("4. Schedule an event/Appointment");
		System.out.println("5. Print event details");
		System.out.println("6. Print contacts by first name");
		System.out.println("7. Print all events alphabetically");
		System.out.println("8. Exit");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();

		return choice;
	}

	public static int submenu2()
	{
		System.out.println("Enter search criteria:");
		System.out.println("1. Name");
		System.out.println("2. Phone Number");
		System.out.println("3. Email Address");
		System.out.println("4. Address");
		System.out.println("5. Birthday");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();
		return choice;
	}

	public static int submenu5()
	{
		System.out.println("Enter search criteria:");
		System.out.println("1. contact name");
		System.out.println("2. Event tittle");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();
		return choice;
	}

	public static int submenu6()
	{
		System.out.println("Enter type:");
		System.out.println("1. event");
		System.out.println("2. appointment");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();
		return choice;
	}


	//1. Add a contact
	//adds a new contact to a contact list implemented using a Binary Search Tree (contacts), inside the method it takes the following information(name,phone number, email address, address,birthday, notes) from the user to create a new contact and add it to the contact list, while makeing sure that the contact name and phone number are unique within the list
	//it takes no inputs, and there is no output since its void
	public static void AddContact(){
		Contact c = new Contact();//1
		System.out.println("Enter the contact\'s name: ");//1
		input.nextLine();//1
		c.name = input.nextLine();//1

		if (!contacts.empty() && contacts.findkey(c.name))//logn+1
		{
			System.out.println("The Contact Name already Exists.");//logn
			return;
		}
		System.out.print("Enter the contact's phone number:");//1
		c.phoneNumber = input.nextLine();//1

		if (!contacts.empty() && (contacts.SearchPhone(c.phoneNumber)))
		{
			System.out.println("The Phone Number already Exists.");
			return;
		}
		System.out.print("Enter the contact's email address: ");//1
		c.emailAddress = input.nextLine();//1

		System.out.print("Enter the contact's address: ");//1
		c.address = input.nextLine();//1

		System.out.print("Enter the contact's birthday: ");//1
		c.birthday = new Date(input.nextLine());//1

		System.out.print("Enter any notes for the contact: ");//1
		c.notes = input.nextLine();//1

		if (contacts.insert(c.name, c))
			System.out.println("Contact added successfully!");
	}

	//2. Search for a contact
	//the method allows the user to search for a contact in a contacts implemented using a Binary Search Tree (contacts) based on different criteria like name,phone number, birthday, email address,address.
	//first it checks if the contcacts list is empty, if its true then it  will provide the user with meaningfull message indicating that the contact is not found, otherwise it will show the search menu.
	//it takes no inputs, and there is no output since its void
	public static void SearchContact()
	{
		int choice = submenu2();
		if (contacts.empty())
			System.out.println("Couldn't Find the contact.");
		else
		{
			switch ( choice )
			{
			case 1:
			{
				System.out.print("Enter the contact's name: ");
				input.nextLine();
				String name = input.nextLine();

				if (!contacts.empty() && contacts.findkey(name))
				{
					System.out.println("Couldn't Find the contact.");

					System.out.println(contacts.retrieve().toString());
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}
			break;

			case 2:
			{
				System.out.print("Enter the contact's phone number:");
				input.nextLine();
				String phonenumber = input.nextLine();

				if (!contacts.empty() && contacts.SearchPhone(phonenumber))
				{
					System.out.println("Couldn't Find the contact.");

					System.out.println(contacts.retrieve());
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}
			break;

			case 3:
			{
				System.out.print("Enter the contact's email address: ");
				input.nextLine();
				String emailaddress = input.nextLine();

				if (!contacts.empty())
				{
					contacts.SearchEmail(emailaddress);
					System.out.println("Contact found!");
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}                
			break;

			case 4:
			{
				System.out.print("Enter the contact's address: ");
				input.nextLine();
				String address = input.nextLine();
				if (!contacts.empty() )
				{
					contacts.SearchAddress(address);
					System.out.println("Contact found!");
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}                
			break;

			case 5:
			{
				System.out.print("Enter the contact's Birthday: ");
				Date birthday = new Date(input.next());
				if (!contacts.empty() )
				{
					contacts.SearchBirthday(birthday);
					System.out.println("Contact found!");
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}
			}
		}            
	}

	//3. Delete a contact 
	//this method allows the user to delete a contact from a contact implmented using a Binary Search Tree (contacts), and it updates the associated events list by removing the contact from any events he was participating in.
	//after initializing a Contact object c, It prompts the user to enter the contact's name, first it checks if the contact BST is empty if its true it prints a message indicating that the contact couldn't be found and returns from the method
	//otherwise, it checks if a contact with the specified name exists in the contact BST using the findkey method of the contacts Binary Search Tree. If the contact is not found, it prints a message indicating that the contact couldn't be found and returns from the method.
	// else, If a match is found, the contact is removed from the event. If the event no longer has any participating contacts, it is removed from the main events list. then it prints a message confirming the successful deletion of the contact and displays its information.
	//it takes no inputs, and there is no output since its void.
	public static void DeleteContact()
	{
		Contact c = new Contact();

		System.out.print("Enter the contact's name: ");
		input.nextLine();
		c.name = input.nextLine();

		if (contacts.empty())
			System.out.println("Contact not found!");
		else
		{

			if ( ! contacts.findkey(c.name))
				System.out.println("Contact not found!");
			else
			{
				c = contacts.retrieve();
				contacts.removeKey(c.name);
				if (! c.events.empty())
				{
					c.events.findFirst();
					for ( int i = 0 ; i < c.events.size ; i++)
					{
						Event e = c.events.retrieve();
						if ( (!events.empty()) && events.search(e) 
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
						{
							Event Update_Event = events.retrieve();

							Update_Event.removeContact(c.name);
							if (Update_Event.contactsNames.empty())
							{
								events.remove(e);
								System.out.println("Delete event, No cantact particapate");
							}
							else
								events.update(Update_Event);

						}
						c.events.findNext();
					}
				}
				System.out.println("Contact Deleted Successfully !");
				System.out.println(c);
			}    
		}        
	}

	//4. Schedule an event
	public static void ScheduleEvent()
	{
		Contact c= new Contact();
		Event e = new Event();

		boolean event_Updated = false;

		int choice = submenu6();
		String type;
		if ( choice == 1 )
		{
			type = "Event";
			e.EventType = true;
			System.out.print("Enter event title: ");
			input.nextLine();
			e.title = input.nextLine();

			System.out.print("Enter contacts name separated by a comma: ");
			String line = input.nextLine();
			String [] names = line.split(",");

			System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
			e.date = new Date (input.next());

			e.time = input.next();

			System.out.print("Enter event location: ");
			input.nextLine();
			e.location = input.nextLine();

			String cNames[]=new String[names.length];
			for ( int i = 0 ; i < names.length ; i++) {
				cNames[i] = names[i].trim();
			}

			for ( int i = 0 ; i < cNames.length ; i++){ 
				c= new Contact();
				c.name = names[i].trim();

				if ( ! contacts.empty() && contacts.findkey(c.name) == true)
				{
					c = contacts.retrieve();
					if (c.addEvent(e))
					{
						// event added to contact
						contacts.update(c.name,c);
						if ( (!events.empty()) && events.search(e) 
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
						{
							Event eventFound = events.retrieve();
							eventFound.contactsNames.insert(c.name);
							events.update(eventFound);
							event_Updated = true;
						}

						if (! event_Updated)  
						{
							e.contactsNames.insert(c.name);
							events.insert(e);
						}
						System.out.println("Event scheduled successfully for " + c.name + "  !");
					}
					else
						System.out.println("Contact has conflict Event for " + c.name + "  !");
				}
				else
					System.out.println("Cantcat " + c.name + " not found !");                                   
			} // end for 
		}  // end schedule event
		else 
		{ // schedule appointment
			type = "Appointment";
			e.EventType = false;
			System.out.print("Enter appointment title: ");
			input.nextLine();
			e.title = input.nextLine();

			System.out.print("Enter contact name: ");
			c.name = input.nextLine();

			if ( ! contacts.empty() && contacts.findkey(c.name) == true)
			{
				c = contacts.retrieve();

				System.out.print("Enter appointment date and time (MM/DD/YYYY HH:MM): ");
				e.date = new Date (input.next());

				e.time = input.next();

				System.out.print("Enter appointment location: ");
				input.nextLine();
				e.location = input.nextLine();

				if ( (!events.empty()) && events.search(e) 
						&& (events.retrieve().date.compareTo(e.date)==0) 
						&& (events.retrieve().time.compareTo(e.time)==0)
						&& (events.retrieve().location.compareTo(e.location)==0)
						&& (events.retrieve().EventType== e.EventType))
				{
					System.out.println("Appointment had been scheduled previously, could not add more contacts, try again ");
				}
				else
				{
					if (c.addEvent(e))
					{
						// event added to contact
						contacts.update(c.name,c);
						e.contactsNames.insert(c.name);
						events.insert(e);
						System.out.println("appointment scheduled successfully!   ");
					}
					else
						System.out.println("Contact has conflict Event/appointment !  ");
				}
			}    
			else
				System.out.println("Couldn't Find the contact.");
		} // end schedule appointment        
	}

	//5. Print event details
	public static void PrintEvent(){
		int choice = submenu5();
		switch ( choice )
		{
		case 1:
		{
			Contact c = new Contact();
			System.out.print("Enter the contact name :  ");
			input.nextLine();
			c.name = input.nextLine();

			if (! contacts.empty() )
			{
				if (contacts.findkey(c.name) == true)
				{
					System.out.println("Contact found !");
					c = contacts.retrieve();

					c.events.findFirst();
					for (int i = 0 ; i < c.events.size ; i++)
					{
						Event e = c.events.retrieve();
						if (!events.empty() && events.search(e) 
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
							System.out.println(events.retrieve().toString());
						c.events.findNext();
					}
					if (c.events.empty())
						System.out.println("No events found for this contact !");
				}
				else
					System.out.println("Couldn't Find the contact.");
			}
			else
				System.out.println("Couldn't Find the contact.");
		}
		break;

		case 2:
		{
			Event e = new Event();
			System.out.print("Enter the event title:  ");
			input.nextLine();
			e.title = input.nextLine();

			if (! events.empty())
			{
				events.findFirst();
				for (int i = 0 ; i < events.size ; i++)
				{   
					if (events.retrieve().compareTo(e) == 0)
					{
						if (events.retrieve().EventType == true)
							System.out.println("Event found!");
						else
							System.out.println("Appointment found!");

						Event ee = events.retrieve();
						System.out.println(ee);
					}
					events.findNext();
				}
			}
			else
				System.out.println("Couldn't Find the Event/Appointment.");
		}
		break;
		}
	}

	//6. Print contacts by first name
	//this method allows the user to search by the first name and then print all contact's information.
	// If contacts with the specified first name are found, they are printed. Otherwise, a message is displayed indicating that the contacts couldn't be found.
	////it takes no inputs, and there is no output since its void
	public static void PrintContactsFirstName(){

		System.out.print("Enter the contact first name:");
		String fname = input.next().trim();

		if (contacts.empty())
			System.out.println("Couldn't Find the Contact.");
		else
			contacts.SearchSameFirstName(fname);
	}

	//7. Print all events alphabetically // O(n)
	//this method allows the user to print all events stored in the event list in alphabetical order.
	//If events are found, they are printed. Otherwise, a message is displayed indicating that the events couldn't be found.
	//it takes no inputs, and there is no output since its void
	public static void PrintAllEvents(){
		if (!events.empty())
			System.out.println(events.toString());
		else
			System.out.println("Couldn't Find the Event.");
	}

	public static void main(String[] args) {
		// TODO code application logic here
		Contact c1=new Contact("Layan Almousa", "0534909456","Layan@gmail.com", "12345, aa street", "29/04/2003", "bye");
		Contact c2=new Contact("Retaj Alghamdi", "0596889324","retaj@gmail.com", "11111, cc street", "29/04/2003", "hellooo");
		Contact c3=new Contact("Anwar Alshamrani", "0551353150","Anwar@gmail.com", "11111, cc street", "23/03/2003", "stay safe");



		contacts.insert("Layan Almousa",c1);
		contacts.insert("Retaj Alghamdi",c2);
		contacts.insert("Anwar Alshamrani",c3); 

		System.out.println("Welcome to the BST Phonebook!");

		int choice;
		do {
			choice = menu();
			switch (choice)
			{
			case 1:
				AddContact();
				break;

			case 2:
				SearchContact();
				break;

			case 3:
				DeleteContact();
				break;

			case 4:
				ScheduleEvent();
				break;

			case 5:
				PrintEvent();
				break;

			case 6:
				PrintContactsFirstName();
				break;

			case 7:
				PrintAllEvents();
				break;

			case 8:
				System.out.println("Goodbye!");
				break;
			default :
				System.out.println("Bad choice! Try again");
			}
			System.out.println("\n\n");
		}while (choice != 8);
	}

}
