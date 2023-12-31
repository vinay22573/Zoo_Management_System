package org.IIITD;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to ZOOtopia!");
        boolean exit = false;
        AdminZoo zooAdmin = new AdminZoo();
        Scanner sc  = new Scanner(System.in);
        Visitor shobhit = new Visitor();
        shobhit.setName("Shobhit");
        shobhit.setAge(19);
        shobhit.setPhoneNumber("857792");
        shobhit.setBalance(100); // Set initial balance to 100
        shobhit.setEmail("Shobhit19");
        shobhit.setPassword("Shobhit19");

        Visitor vinay = new Visitor();
        vinay.setName("Vinay");
        vinay.setAge(20);
        vinay.setPhoneNumber("987160");
        vinay.setBalance(100); // Set initial balance to 100
        vinay.setEmail("Vinay20");
        vinay.setPassword("Vinay20");

        Visitor aryan = new Visitor();
        aryan.setName("Aryan");
        aryan.setAge(21);
        aryan.setPhoneNumber("884029");
        aryan.setBalance(100); // Set initial balance to 100
        aryan.setEmail("Aryan21");
        aryan.setPassword("Aryan21");
        Visitor.getRegisteredVisitors().add(shobhit);
        Visitor.getRegisteredVisitors().add(vinay);
        Visitor.getRegisteredVisitors().add(aryan);

        SpecialDeal deal1 = new SpecialDeal("Buy 2 tickets",15);
        SpecialDeal deal2 = new SpecialDeal("Buy 3 tickets",30);
        AdminZoo.specialDeal.put(deal1,"Buy 2 tickets");
        AdminZoo.specialDeal.put(deal2,"Buy 3 tickets");

        Discount d1 = new Discount("Minor",10);
        Discount d2 = new Discount("Senior",20);
        AdminZoo.discounts.add(d1);
        AdminZoo.discounts.add(d2);

        zooAdmin.attractions.put("A00", new Attraction("30.0", "Jungle Safari", "An adventurous ride through the jungle."));
        zooAdmin.attractions.put("A01", new Attraction("25.0", "Botanical Garden", "Explore the diverse plant life in our botanical garden."));
        zooAdmin.attractions.put("A02", new Attraction("20.0", "Dinosaur Show", "Witness the fascinating world of dinosaurs in action"));

        while (!exit) {
            System.out.println("1. Enter as Admin\n2. Enter as a Visitor\n3. View Special Deals\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:

                    System.out.print("Enter Admin Username: ");
                    String adminUsername = sc.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminPassword = sc.nextLine();


                    if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
                        System.out.println("Logged in as Admin.");
                        adminMenu(zooAdmin);
                        // Implement the admin menu and its functionalities
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;

                case 2:
                    enterasaVisitor();
                    break;
                case 3:
                    // View Special Deals functionality
                    // Add your code here for viewing special deals
                    Visitor.viewSpecialDeals(AdminZoo.specialDeal);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }

    private static void enterasaVisitor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("Enter your choice:");
        int choice  = sc.nextInt();
        sc.nextLine(); // clear the buffer
        switch (choice){
            case 1:
                Visitor.register();
                break;
            case 2:
                // call the login method
                Visitor.login();
                break;
            default:
                System.out.println("Enter a valid choice.");
                return;
        }
    }


    public static void adminMenu(AdminZoo zooAdmin) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin Menu:");
            System.out.println("1. Manage Attractions");
            System.out.println("2. Manage Animals");
            System.out.println("3. Schedule Events");
            System.out.println("4. Set Discounts");
            System.out.println("5. Set Special Deal");
            System.out.println("6. View Visitor Stats");
            System.out.println("7. View Feedback");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int adminChoice = sc.nextInt();
            sc.nextLine(); // Clear the buffer
            switch (adminChoice) {
                case 1:
                    zooAdmin.manageAttractions();
                    break;
                case 2:
                    zooAdmin.manageAnimals();
                    break;
                case 3:
                    zooAdmin.scheduleEvents();
                    break;
                case 4:
                    zooAdmin.setDiscounts();
                    break;
                case 5:
                    System.out.println("Enter the name of specialDeal:");
                    String name = sc.nextLine();
                    System.out.println("Enter the percentage of SpecialDeal Discount:");
                    double percentage = sc.nextDouble();
                    sc.nextLine(); // Clear the buffer
                    zooAdmin.setSpecialDeal(name, percentage);
                    break;
                case 6:
                    zooAdmin.viewVisitorStats();
                    break;
                case 7:
                    zooAdmin.viewFeedback();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    public static void visitorMenu(Visitor visitor) {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;
        while (!logout) {
            System.out.println("Visitor Menu:");
            System.out.println("1. Explore the Zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View Special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Logout");
            System.out.print("Enter your choice: ");
            int visitorChoice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (visitorChoice) {
                case 1:
//                    visitor.exploreZoo();
                    break;
                case 2:
//                    visitor.buyMembership();
                    break;
                case 3:
                    // visitor.buyTickets(visitor.getAttractions());
                    break;
                case 4:
                    // visitor.viewDiscount(visitor.getDiscounts());
                    break;
                case 5:
//                     visitor.viewSpecialDeals(visitor.getSpecialDeal());
                    break;
                case 6:
                    System.out.println("Visiting Animals...");
                    break;
                case 7:
                    System.out.println("Visiting Attractions...");
                    break;
                case 8:
                    System.out.println("Leave Feedback...");
                    break;
                case 9:
                    logout = true;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
