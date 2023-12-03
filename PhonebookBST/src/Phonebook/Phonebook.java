package Phonebook;
import java.util.Date;
import java.util.Scanner;

/*

This class represents a phonebook application. It includes a field for a BST data structure that stores contacts,
as well as methods for interacting with the contact BST such as adding, searching, and deleting contacts.
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

	public static int SearchContactMenu()
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

	public static int PrintEventMenu()
	{
		System.out.println("Enter search criteria:");
		System.out.println("1. contact name");
		System.out.println("2. Event tittle");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();
		return choice;
	}

	public static int PrintContactFname()
	{
		System.out.println("Enter type:");
		System.out.println("1. event");
		System.out.println("2. appointment");
		System.out.println("\nEnter your choice: ");
		int choice = input.nextInt();
		return choice;
	}


	/*1. Add a contact
	this method is used to add a new contact to a contact BST called: contacts.
        It prompts the user to enter the name, phone number, email address, address, birthday, and notes, 
	creates a new contact with this information and adds it to the contact BST while making sure that the contact’s name and phone number are unique,
        it takes no inputs(parameters), and there is no output since its void.*/
	public static void AddContact(){
		Contact c = new Contact();
		System.out.println("Enter the contact\'s name: ");
		input.nextLine();
		c.name = input.nextLine();

		if (!contacts.empty() && contacts.findkey(c.name))
		{
			System.out.println("The Contact Name already Exists.");
			return;
		}
		System.out.print("Enter the contact's phone number:");
		c.phoneNumber = input.nextLine();

		if (!contacts.empty() && (contacts.SearchPhone(c.phoneNumber)))
		{
			System.out.println("The Phone Number already Exists.");
			return;
		}
		System.out.print("Enter the contact's email address: ");
		c.emailAddress = input.nextLine();

		System.out.print("Enter the contact's address: ");
		c.address = input.nextLine();

		System.out.print("Enter the contact's birthday: ");
		c.birthday = new Date(input.nextLine());

		System.out.print("Enter any notes for the contact: ");
		c.notes = input.nextLine();

		if (contacts.insert(c.name, c))
			System.out.println("Contact added successfully!");
	}

	/*2. Search for a contact
	the method allows the user to search for a contact in a contacts BST called ‘contacts’ 
        based on different criteria like name, phone number, birthday, email address, and address.
       First it checks if the contacts BST is empty, if it's empty it will provide the user with meaningful message
       indicating that the contact is not found, otherwise it will show the search menu to prompt the user to choose a criteria 
       and searches in the BST for a contact or contacts that share the same attribute value as the user’s input,
       it takes no inputs(parameters), and there is no output since its void .*/
	public static void SearchContact()
	{
		int choice = SearchContactMenu();
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

				if (!contacts.empty() && contacts.findkey(name))//check if contacts BST is empty and if the name of the contact exist
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
				String phonenumber = input.nextLine();

				if (!contacts.empty() && contacts.SearchPhone(phonenumber))//check if contacts BST empty and the if the phone number is exist
				input.nextLine();
				{
					System.out.println("contact is found!");

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
				String emailAddress = input.nextLine();

				if (!contacts.empty())
				{
					contacts.SearchEmail(emailAddress);//printing will be inside the method 
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
				if (!contacts.empty())
				{
					contacts.SearchAddress(address);//printing will be inside the method 
					break;
				}
				System.out.println("Couldn't Find the contact.");
			}                
			break;

			case 5:
			{
				System.out.print("Enter the contact's Birthday: ");
				Date birthday = new Date(input.next());
				if (!contacts.empty())
				{
					contacts.SearchBirthday(birthday);//printing will be inside the method
					break;
				}
				System.out.println("Couldn't Find the contact.");
				
			}
			}
		}            
	}

	/*3. Delete a contact 
	this method allows the user to delete a contact from the contacts BST and updates the events list by removing his/her name
        from all the events associated with the deleted contact, and if it was an appointment or if the contact was the only one it deletes the event. 
        First, It prompts the user to enter the contact's name, checks if the contact BST is empty if it is empty it prints a message indicating that the contact
	couldn't be found, otherwise, it checks if a contact with the specified name exists in the contact BST using the findkey method of the contacts BST.
        If the contact’s name is not found, it prints a message indicating that the contact couldn't be found, but if a match is found, it checks if the contact has events
	then the contact’s name will be removed from the events and all the appointments with that contact will be deleted. 
        Then it prints a message confirming the successful deletion of the contact and displays its information,
	it takes no inputs (Parameter), and there is no output since its void.*/
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
			else// contact name is found
			{
				c = contacts.retrieve();
				contacts.removeKey(c.name);
				if (! c.events.empty())
				{
					c.events.findFirst();
					for ( int i = 0 ; i < c.events.size ; i++)//it iterate through the event list of the contact
					{
						Event e = c.events.retrieve();
						if ( (!events.empty()) && events.search(e) 
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
						{
							Event Update_Event = events.retrieve();//to store the founded event

							Update_Event.removeContact(c.name);
							if (Update_Event.contactsNames.empty())//cheack if this contact is the only/last contact in the event
							{
								events.remove(e);//update the big event list
								System.out.println("Delete event, No cantact particapate");
							}
							else//there are other contact
								events.update(Update_Event);//update the big event list

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
	/*This method is responsible for scheduling either an event or an appointment based on the user’s input. 
         Before scheduling, it checks if the contact exists in the contacts BST, If the contact does not exist it will provide a meaningful message ,
	 otherwise, it will read the user input, if it is an appointment it will check for conflict 
         either in the chosen contact’s previous events and appointments (if they are scheduled in the same date and time),
	 or if other contacts have the same appointment at the same location, date and time. it takes no inputs(parameters), and there is no output since its void.*/
	public static void ScheduleEvent()
	{
		Contact c= new Contact();
		Event e = new Event();

		boolean event_Updated = false;

		int choice = PrintContactFname();
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

			String cNames[]=new String[names.length];// this array store the contact names without comma
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
						if ( (!events.empty()) && events.search(e) // if the event alredy exists it will only update the contacts names 
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
						{
							Event eventFound = events.retrieve();
							eventFound.contactsNames.insert(c.name);//store the contact name inside the event
							events.update(eventFound);//update the big event list
							event_Updated = true;
						}

						if (! event_Updated)//if its a new event
						{
							e.contactsNames.insert(c.name);
							events.insert(e);
						}
						System.out.println("Event scheduled successfully for " + c.name + "  !");
					}
					else// event is not added
						System.out.println("Contact has conflict Event for " + c.name + "  !");
				}
				else// if contact is not found
					System.out.println("Contcat " + c.name + " not found !");                                   
			} // end for 
		}  // end schedule event
		else 
		{ // schedule appointment
			type = "Appointment";
			e.EventType = false;//not event
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

				if ( (!events.empty()) && events.search(e) //check for conflict
						&& (events.retrieve().date.compareTo(e.date)==0) 
						&& (events.retrieve().time.compareTo(e.time)==0)
						&& (events.retrieve().location.compareTo(e.location)==0)
						&& (events.retrieve().EventType== e.EventType))
				{
					System.out.println("Appointment had been scheduled previously, could not add more contacts, try again ");
				}
				else//no conflict
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
	/* this method prints event details by asking the user to choose between two search criteria: contact name or event title. 
          If the user chooses to print the event using contact name it checks if contacts BST is not empty and the specified contact name is found, 
	  it will look for all the events the contact with the specified name has and prints them. If it's not found or the contacts BST is empty,
          it will display a meaningful message.
          If the user chooses to print by searching by event title it will check if event list is empty and if there is an event with the same title and print its details,
	  it takes no inputs(parameters), and there is no output since its void. */
	public static void PrintEvent(){
		int choice = PrintEventMenu();
		switch ( choice )
		{
		case 1:
		{
			Contact c = new Contact();
			System.out.print("Enter the contact name :  ");
			input.nextLine();
			c.name = input.nextLine();

			if (! contacts.empty() )//check if there is a contact before printing the event detalis
			{
				if (contacts.findkey(c.name) == true)
				{
					System.out.println("Contact found !");
					c = contacts.retrieve();

					c.events.findFirst();
					for (int i = 0 ; i < c.events.size ; i++)
					{
						Event e = c.events.retrieve();
						if (!events.empty() && events.search(e) //check if the event matches with any event in the big event list
								&& (events.retrieve().date.compareTo(e.date)==0) 
								&& (events.retrieve().time.compareTo(e.time)==0)
								&& (events.retrieve().location.compareTo(e.location)==0)
								&& (events.retrieve().EventType== e.EventType))
							System.out.println(events.retrieve().toString());//print the event detail
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
			System.out.print("Enter the event's title:  ");
			input.nextLine();
			e.title = input.nextLine();

			if (! events.empty())
			{
				events.findFirst();
				for (int i = 0 ; i < events.size ; i++)//iterate through the event list 
				{   
					if (events.retrieve().compareTo(e) == 0)//to find e by comparing the title with already exising events
					{
						if (events.retrieve().EventType == true)//if the EventType is true then its event
							System.out.println("Event found!");
						else
							System.out.println("Appointment found!");

						Event ee = events.retrieve();
						System.out.println(ee);//printing the event or Appointment information
					}
					events.findNext();
				}
			}
			else // the event list is empty
				System.out.println("Couldn't Find the Event/Appointment."); 
		}
		break;//exist case 2
		}
	}

	//6. Print contacts by first name
	/*This method allows the user to search by the first name and then print all the first-name-matched-contacts information.
           If contacts with the specified first name are found, they are printed. 
	   Otherwise, a message is displayed indicating that the contacts couldn't be found. it takes no inputs(parameters), and there is no output since its void.*/
	public static void PrintContactsFirstName(){

		System.out.print("Enter the contact first name:");
		String fname = input.next().trim(); 

		if (contacts.empty())
			System.out.println("Couldn't Find the Contact.");
		else
			contacts.SearchSameFirstName(fname);// the printing is in the function
	}

	//7. Print all events alphabetically // O(n)
	/*This method allows the user to print all events scheduled By all Phonebook contacts in alphabetical order,
         we were able to accomplish that by creating a LinkedList in the main class ‘Phonebook’ called events and store each scheduled event by each contact in the list
	 alphabetically,to be inserted sorted and only use toString() in the printing method which makes its time complexity a O(n).
         If the event list is not empty, the events are printed. Otherwise, a message is displayed indicating that the events couldn't be found,
	 it takes no inputs(parameters), and there is no output since its void.*/
	public static void PrintAllEvents(){
		if (!events.empty())
			System.out.println(events.toString());//the events list is already sorted alphabetically
		else
			System.out.println("There are no events.");
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
				System.out.println("wrong choice! Try again");
			}
			System.out.println("\n\n");
		}while (choice != 8);
	}

}
