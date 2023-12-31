package org.IIITD;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;


public class Visitor extends Person {
    List<String> attractionsticketbought= new ArrayList<>();
    private static List<Visitor> registeredVisitors = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    public static List<Visitor> getRegisteredVisitors() {
        return registeredVisitors;
    }

    private static AdminZoo admin = new AdminZoo();

    public static Visitor checkVisitorCredentials(String email, String password) {
        for (Visitor visitor : registeredVisitors) {
            if (visitor.getEmail().equals(email) && visitor.getPassword().equals(password)) {
                return visitor;
            }
        }
        System.out.println("Visitor Credentials did not matched. \n Exiting...");
        return null; // Return null if no matching credentials are found
    }
    public void buyTickets(Map<String, Attraction> attractions) {
        // this.attractions = attractions; // Set the local attractions variable
        if(membershiptype()==0){
            System.out.println("You need to buy a Membership plan first.");
            System.out.println(
                    "1. Basic Membership (₹20)\n" +
                            "2. Premium Membership (₹50)");
            System.out.println("If willing to buy press 1 else press any other number");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    buyMembership();
                    break;
                default:
                    System.out.println("Sorry!, you need to buy membership first.");
                    return;
            }


        }
        if(this.membershiptype()!=0){
            System.out.println("Buy Tickets:");
            System.out.print("Enter the number of tickets: ");
            int numTickets = scanner.nextInt();


            if (numTickets > 0) {
                System.out.println("Buy Tickets:");
                System.out.println("Select an Attraction to Buy a Ticket:");
                int count = 1;
                for (Map.Entry<String, Attraction> entry : attractions.entrySet()) {
                    System.out.println(count + ". " + entry.getKey() + " (₹" + entry.getValue().getTicketPrice() + ")");
                    count++;
                }
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= attractions.size()) {
                    Attraction selectedAttraction = null;
                    int attractionCount = 1;
                    for (Map.Entry<String, Attraction> entry : attractions.entrySet()) {
                        if (attractionCount == choice) {
                            selectedAttraction = entry.getValue();
                            break;
                        }
                        attractionCount++;
                    }
                    if(selectedAttraction != null){
                        double ticketPrice = selectedAttraction.getTicketPrice();
                        double totalCost = ticketPrice * numTickets;


                        if (super.getBalance() >= totalCost) {
                            double balance = getBalance();
                            balance = getBalance() -totalCost;
                            this.setBalance(balance);
                            attractionsticketbought.add(selectedAttraction.getAttractionName());
                            System.out.println("The ticket for " + selectedAttraction.getAttractionName() + " was purchased successfully. Your balance is now " + balance + ".");
                        } else {
                            System.out.println("Insufficient balance. Please recharge your account.");
                            System.out.println("To recharge press 1. else exit press 2");
                            int choic  = sc.nextInt();
                            sc.nextLine(); // clear the buffer
                            switch (choic){
                                case 1:
                                    this.rechargeBalance();
                                default:
                                    System.out.println("Sorry, with this low balance you cannot proceed further. \n Exiting....");
                            }
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Please enter a valid number of tickets.");
            }
        }
    }
    @Override
    public double getBalance() {
        return super.getBalance();
    }

    @Override
    public void setBalance(double balance) {
        super.setBalance(balance);
    }

    public static void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Visitor Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Visitor Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter Visitor Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter Visitor Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // Clear the buffer
        System.out.print("Enter Visitor Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Visitor Password: ");
        String password = scanner.nextLine();
        Visitor vis = new Visitor();
        vis.setName(name);
        vis.setAge(age);
        vis.setPhoneNumber(phoneNumber);
        vis.setBalance(balance);
        vis.setEmail(email);
        vis.setPassword(password);
        // Add the new visitor to the registeredVisitors list
        registeredVisitors.add(vis);
        System.out.println("Registration is successful.");
    }
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Visitor Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Visitor Password: ");
        String password = scanner.nextLine();

        Visitor loggedInVisitor = Visitor.checkVisitorCredentials(email, password);

        if (loggedInVisitor != null) {
            System.out.println("Login Successful.");
            Visitor.visitorMenu(loggedInVisitor);
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }
    }
    Map<String,Attraction> attr=admin.getAttr();
    private static void visitorMenu(Visitor visitor) {
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
                    visitor.exploreZoo();
                    break;
                case 2:
                    visitor.buyMembership();
                    break;
                case 3:
                     Map<String,Attraction> attr = admin.getAttr();
                     visitor.buyTickets(attr);
                    break;
                case 4:
                     visitor.viewDiscount(AdminZoo.discounts);
                    break;
                case 5:
                    Visitor.viewSpecialDeals(AdminZoo.specialDeal);
                    break;
                case 6:
                    System.out.println("Visiting Animals...");
                    break;
                case 7:
                    System.out.println("Visiting Attractions...");
                    break;
                case 8:
                    System.out.println("Leave Feedback...");
//                    this.giveFeedback(AdminZoo.feedback);
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
    Scanner sc = new Scanner(System.in);
    //EXPLORE THE ZOO
    public void exploreZoo() {
        boolean exit = false;
        while (!exit){
            System.out.println("1. View Attractions");
            System.out.println("2. View Animals");
            System.out.println("3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    admin.getAttractions();
                    viewAttractions();
                    break;
                case 2:
                    admin.getAnimals();
                    viewAnimals();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    private void viewAttractions() {
        System.out.println("Enter the attraction number which you want to visit:");
        System.out.println("Enter your choice:");
        int choice  = scanner.nextInt();
        scanner.nextLine();// Clear the Buffer
        System.out.println("ZOOtopia offers an adventure ride that allows you to explore unexplored trails. Buy your ticket now!");
    }
    private void viewAnimals() {
        System.out.println("Want to see these animals go and Visit the Animals.");
    }
    // BUY MEMBERSHIP
    public void buyMembership() {
        if(this.getBalance()<50){
            System.out.println("Your Balance is low you cannot buymembership. Firstly recharge.");
            System.out.println("To recharge press 1. else exit press 2");
            int choice  = sc.nextInt();
            sc.nextLine(); // clear the buffer
            switch (choice){
                case 1:
                    this.rechargeBalance();
                default:
                    System.out.println("Sorry, with this low balance you cannot proceed further. \n Exiting....");
            }
        }
        else if (this.getBalance() > 50){
            if (membershiptype() == 0) {
                System.out.println("Buy Membership:\n" +
                        "1. Basic Membership (₹20)\n" +
                        "2. Premium Membership (₹50)\n" + "3. Exit");
                System.out.print("Enter your choice:");
                int choice = scanner.nextInt();
                scanner.nextLine();// to clear the buffer
                List<Discount> d = admin.getDiscountss();
                this.viewDiscount(d);
                System.out.println("Apply Discount Code:");
                String code = scanner.nextLine();

                String membershipType = "";
                boolean exit = false;
                boolean disc = false;
                while (!exit) {
                    switch (choice) {
                        case 1:
                            membershipType = "Basic Membership";
                            setmembershiptype(1);
                            exit = true;
                            break;
                        case 2:
                            membershipType = "Premium Membership";
                            setmembershiptype(2);
                            exit = true;
                            break;
                        case 3:
                            disc = true;
                            exit = true;
                            break;
                        default:
                            System.out.println("Please select a valid option");
                    }
                }
                if (!disc) {
                    // Check if the entered code matches any discount code in the list
                    boolean codeMatched = false;
                    for (Discount discount : d) {
                        if (discount.getDiscountCode().equals(code)) {
                            double percentage = discount.getPercentage();
                            if (choice == 1) {
                                setBalance(getBalance() - (20 - (20 * (percentage / 100))));
                            } else if (choice == 2) {
                                setBalance(getBalance() - (50 - (50 * (percentage / 100))));
                            }
                            codeMatched = true;
                            break;
                        }
                    }
                    if (!codeMatched) {
                        System.out.println("Invalid discount code. No discount applied.");
                    }
                    System.out.println(membershipType + " purchased successfully. Your balance is now ₹" + getBalance());
                }
            } else {
                System.out.println("You already have a membership.");
            }
        }
        else{
            System.out.println("Low Balance Cannot buy membership.");
        }
    }



    // BUY TICKETS

    public void viewDiscount(List<Discount> discounts) {
        System.out.println("View Discounts:");
        if (discounts.isEmpty()) {
            System.out.println("No discounts to show.");
            return;
        }
        int count = 1;
        for (Discount discount : discounts) {
            String discountCode = discount.getDiscountCode();
            String discountName = discount.getDiscountName();
            System.out.println(count + ". " + discountName + " - " + discountCode);
            count++;
        }
        return;
    }

    // SPECIAL DEAL WORKS ON  NUMBER OF TICKETS
    public static void viewSpecialDeals(Map<SpecialDeal, String> specialDeal) {
        System.out.println("Special Deals:");
        int count = 1;
        for (Map.Entry<SpecialDeal, String> entry : specialDeal.entrySet()) {
            System.out.println(count + ". " + entry.getKey().getDiscountName() + " - " + entry.getKey().getDiscountPercentage());
            count++;
        }
    }

    // HOW TO VISIT AN ANIMAL
    public void visitAnimal(String animal){
        // Implementation for visiting an animal, feeding it, or reading about it
    }
    // VISIT ATTRACTIONS
    public void visitAttractions() {
        System.out.println("List of attractions:");
        admin.getAttractions();

        System.out.print("Enter the name of the attraction you want to visit: ");
        String attractionName = scanner.nextLine();

        if (attractionsticketbought.contains(attractionName)) {
            for (Attraction attraction : admin.getAttr().values()) {
                if (attraction.getAttractionName().equals(attractionName)) {
                    System.out.println("Description of " + attractionName + ":");
                    System.out.println(attraction.getDescription());
                    // Show the description of the attraction here
                    break;
                }
            }
        } else if (admin.getAttr().containsKey(attractionName)) {
            System.out.println("You have not bought a ticket for this attraction.");
            System.out.println("Press 1 to buy or 2 to exit.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    buyTickets(admin.getAttr());
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } else {
            System.out.println("Please enter a valid attraction name.");
        }
    }

    public void giveFeedback(Map<String,String> feedback) {
        Scanner scanner  = new Scanner(System.in);
        String visitorName = this.getName();
        System.out.println("Enter your feedback: ");
        String feedbackText = scanner.nextLine();
        feedback.put(visitorName, feedbackText);
        System.out.println("Thank you for your feedback.");
    }
    public void rechargeBalance(){
        System.out.println("Enter the amount with which you want to recharge:");
        double money = sc.nextDouble();
        sc.nextLine();// cler the bufferf
        if(money<0){
            System.out.println("Invalide amount entered. \n Exiting...");
            return;
        }
        double balance  = this.getBalance();
        this.setBalance(balance + money);
    }


}
