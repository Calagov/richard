import java.util.Scanner;

public class ContactList {
    private static final int MAX_CONTACTS = 4;
    private static String[] names = new String[MAX_CONTACTS];
    private static String[] phoneNumbers = new String[MAX_CONTACTS];
    private static String[] emails = new String[MAX_CONTACTS];
    private static int contactCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Contact List Menu:");
            System.out.println("1. Add Contact");
            System.out.println("2. Remove Contact");
            System.out.println("3. View Contacts");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addContact(scanner);
                    break;
                case 2:
                    removeContact(scanner);
                    break;
                case 3:
                    viewContacts();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void addContact(Scanner scanner) {
        if (contactCount >= MAX_CONTACTS) {
            System.out.println("Contact list is full!");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        if (validatePhoneNumber(phoneNumber) && validateEmail(email)) {
            names[contactCount] = name;
            phoneNumbers[contactCount] = phoneNumber;
            emails[contactCount] = email;
            contactCount++;
            System.out.println("Contact added successfully.");
        } else {
            System.out.println("Invalid input. Contact not added.");
        }
    }

    private static void removeContact(Scanner scanner) {
        System.out.print("Enter the index of the contact to remove (0 to " + (contactCount - 1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (index >= 0 && index < contactCount) {
            for (int i = index; i < contactCount - 1; i++) {
                names[i] = names[i + 1];
                phoneNumbers[i] = phoneNumbers[i + 1];
                emails[i] = emails[i + 1];
            }
            contactCount--;
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Invalid index. Contact not removed.");
        }
    }

    private static void viewContacts() {
        System.out.printf("%-5s %-20s %-15s %-30s%n", "Index", "Name", "Phone Number", "Email");
        for (int i = 0; i < contactCount; i++) {
            System.out.printf("%-5d %-20s %-15s %-30s%n", i, names[i], phoneNumbers[i], emails[i]);
        }
    }

    private static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{11}");
    }

    private static boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
}
