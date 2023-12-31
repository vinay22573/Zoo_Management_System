package org.IIITD;
import java.sql.SQLOutput;
import java.util.*;

public class AdminZoo implements Admin {
    Map<String, Attraction> attractions = new HashMap<>();// stores the name of attraction : attraction
    Map<Animal,Integer> animals = new HashMap<>();// stores the animal and its count in the zoo
    static List<Discount> discounts = new ArrayList<>();
    static Map<SpecialDeal,String> specialDeal = new HashMap<>();// SpecialDeal  : name as string
    static Map<String,String> feedback = new HashMap<>();// Name of feedback giver : his/her feedback
    // Other necessary fields
    Scanner sc = new Scanner(System.in);
    public List<Discount> getDiscountss() {
        return discounts;
    }
    public Map<String, Attraction> getAttr(){
        return attractions;
    }



    public void getAttractions() {
        if (attractions.isEmpty()) {
            System.out.println("No attractions currently available.");
        } else {
            System.out.println("List of Attractions:");
            int count = 1;
            for (Map.Entry<String, Attraction> entry : attractions.entrySet()) {

                System.out.println(count + ". " + entry.getKey());
                count++;
            }

        }
    }
    public void getAnimals(){
        if (animals.isEmpty()) {
            System.out.println("No animals currently available.");
        } else {
            System.out.println("List of Animals:");
            for (Map.Entry<Animal, Integer> entry : animals.entrySet()) {
                System.out.println(entry.getKey().getAnimalName() + " : " + entry.getKey().getAnimalType());
            }
        }
    }

    public Map<SpecialDeal, String> getSpecialDeal() {
        return specialDeal;
    }

    int choice;
    public void setSpecialDeal(Map<SpecialDeal, String> specialDeal) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of Special Deal:");
        String name = sc.nextLine(); // Assuming the special deal is identified by a name
        System.out.println("Enter the discount percentage on the Special Deal:");
        double percentage = sc.nextDouble();
        sc.nextLine(); // Clear the buffer

        SpecialDeal newSpecialDeal = new SpecialDeal(name, percentage);
        specialDeal.put(newSpecialDeal, name);

        System.out.println("Special Deal added successfully.");
    }



    // MANAGE ATTRACTIONS
    public void manageAttractions() {
        boolean exit = false;
        while(!exit){
            System.out.println("Manage Attractions:");
            System.out.println("1. Add Attraction\n" +
                    "2. View Attractions\n" +
                    "3. Modify Attraction\n" +
                    "4. Remove Attraction\n" +
                    "5. Exit");
            System.out.println("Enter your choice:");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    this.addAttraction();
                    break;
                case 2:
                    this.viewAttractions();
                    break;
                case 3:
                    this.modifyAttraction();
                    // I am not clear with this that what to do with modify attraction ask
                    // someone
                    break;
                case 4:
                    System.out.println("Enter the attraction Code No (like A00) which you want to remove:");
                    String attractionName = sc.nextLine();
                    this.removeAttraction(attractionName);

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Enter valid input");
            }
        }
    }
    private void addAttraction(){
        // Example implementation
        System.out.print("Enter Attraction Name:");
        String name = sc.nextLine();
        System.out.println("Enter Attraction Description:");
        String descpn = sc.nextLine();

        // Getting the ID for the new attraction
        String newAttractionId;
        if (attractions.isEmpty()) {
            newAttractionId = "A00";
        } else {
            String lastAttractionId = null;
            for (String key : attractions.keySet()) {
                lastAttractionId = key;
            }
            newAttractionId = generateNextAttractionId(lastAttractionId);
        }

        // Creating a new Attraction object
        Attraction newAttraction = new Attraction(newAttractionId, name, descpn);

        // Adding the new attraction to the map
        attractions.put(newAttractionId, newAttraction);

        System.out.println("Attraction added successfully.");
    }


    private void viewAttractions() {
        // Check if the attractions map is empty
        if (attractions.isEmpty()) {
            System.out.println("Sorry, no attractions to show.");
        } else {
            System.out.println("List of Attractions:");
            int counter = 1;
            for (Map.Entry<String, Attraction> entry : attractions.entrySet()) {
                System.out.print(counter + ". ");
                System.out.print(entry.getKey() + " - " + entry.getValue().getAttractionName());
                System.out.println();
                counter++;
            }
        }
    }

    private void removeAttraction(String attractionName) {


        if(attractions.containsKey(attractionName)) {
            // Remove the attraction based on the provided attractionName
            attractions.remove(attractionName);
            System.out.println("Attraction removed successfully.");
        }
        else {
            System.out.println("Attraction not found.");
        }
    }



    private void modifyAttraction() {
        System.out.println("Enter the code of the attraction you want to modify:");
        String attractionCode = sc.nextLine();

        if (attractions.containsKey(attractionCode)) {
            System.out.println("Enter the new description:");
            String newDescription = sc.nextLine();

            Attraction attraction = attractions.get(attractionCode);
            attraction.setDescription(newDescription);

            System.out.println("Attraction description modified successfully.");
        } else {
            System.out.println("Attraction not found.");
        }
    }



    private String generateNextAttractionId(String lastAttractionId) {
        // Assuming the format of the ID is a letter followed by a number
        char prefix = lastAttractionId.charAt(0);
        int num = Integer.parseInt(lastAttractionId.substring(1)) + 1;
        return prefix + String.format("%02d", num); // Assuming the number is 2 digits
    }






    // MANAGE ANIMAL DETAILS
    public void manageAnimals() {

        // There should be two more methods helpers -- first abouttheanimal
        // and second which shows the list of animals which are still in the zoo as we are changing the list dynamically
        boolean exit = false;
        while (!exit) {
            System.out.println("Manage Animals:");
            System.out.println("1. Add Animal");
            System.out.println("2. Update Animal Details");
            System.out.println("3. Remove Animal");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter the name of Animal:");
                    String animalName  = sc.nextLine();
                    System.out.println(" Mammals      ---> 1");
                    System.out.println(" Amphibians   ---> 2");
                    System.out.println(" Reptiles     ---> 3");
                    System.out.println("Enter the animal type:");
                    int animalType = sc.nextInt();
                    sc.nextLine(); // Clear the buffer

                    this.addAnimal(animalName,animalType );
                    break;
                case 2:
                    this.updateAnimalDetails();
                    break;
                case 3:
                    System.out.print("Enter the name of animal which has to be Removed:");
                    String animalNameTobeRemoved = sc.nextLine();
                    this.removeAnimal(animalNameTobeRemoved);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Enter valid input.");
            }
        }
    }
    private void addAnimal(String animalName, int animalType) {
        Animal newAnimal = new Animal(animalName, animalType); // Create a new animal object
        animals.put(newAnimal, 1); // Add the new animal to the map with an initial count of 1
        System.out.println("Animal added to Zoo");
    }
    private void removeAnimal(String animalName) {
        boolean found = false;
        for (Map.Entry<Animal, Integer> entry : animals.entrySet()) {
            Animal animal = entry.getKey();
            if (animal.getAnimalName().equals(animalName)) {
                animals.remove(animal);
                found = true;
                System.out.println("Animal removed successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Animal not found in the zoo.");
        }
    }


    private void updateAnimalDetails() {
        System.out.println("Enter the name of the animal you want to update:");
        String animalNameToUpdate = sc.nextLine();

        boolean found = false;
        for (Animal animal : animals.keySet()) {
            if (animal.getAnimalName().equals(animalNameToUpdate)) {
                while (true) {
                    System.out.println("Enter the new type of the animal:");
                    System.out.println("1. Mammal");
                    System.out.println("2. Amphibian");
                    System.out.println("3. Reptile");

                    int newAnimalType = sc.nextInt();
                    sc.nextLine(); // Clear the buffer

                    switch (newAnimalType) {
                        case 1:
                            animal.setAnimalType(1);
                            System.out.println("Animal type updated to Mammal.");
                            break;
                        case 2:
                            animal.setAnimalType(2);
                            System.out.println("Animal type updated to Amphibian.");
                            break;
                        case 3:
                            animal.setAnimalType(3);
                            System.out.println("Animal type updated to Reptile.");
                            break;
                        default:
                            System.out.println("Invalid input. Enter a valid Animal type:");
                    }
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("Animal not found in the zoo.");
        }
    }







    // SCHEDULING EVENTS
    public void scheduleEvents() {
        // Implementation for scheduling events
        System.out.println("Schedule events");
    }



    // SETTING DISCOUNTS
    public void setDiscounts() {
        // There should be one more helper method which shows the current discount list and about its percentage as we are changing the discount list/map dynamically through our add,remove, modify discount
        // so it should be visible after what change has been done
        boolean exit = false;
        while (!exit) {
            System.out.println("Setting Discounts:");
            System.out.println("1. Add Discount");
            System.out.println("2. Remove Discount");
            System.out.println("3. Modify Discount");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear the buffer

            switch (choice) {
                case 1:
                    this.addDiscount();
                    break;
                case 2:
                    this.removeDiscount();
                    break;
                case 3:
                    this.modifyDiscount();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid input. Enter valid input.");
            }
        }
    }
    private void addDiscount(){
        System.out.println("Enter the discount Category:");
        String Discountname = sc.nextLine();
        System.out.println("Enter the discount percentage:");
        int percentage = sc.nextInt();
        discounts.add(new Discount(Discountname,percentage));
        System.out.println("Discount added Successfully.");
    }
    private void removeDiscount() {
        System.out.println("Enter the discount name to remove:");
        String discountNameToRemove = sc.nextLine();

        // Iterate through the discounts ArrayList
        for (int i = 0; i < discounts.size(); i++) {
            Discount discount = discounts.get(i);

            // Check if the current discount name matches the one to remove
            if (discount.getDiscountName().equals(discountNameToRemove)) {
                discounts.remove(i); // Remove the discount at index i
                System.out.println("Discount removed successfully.");
                return; // Exit the method after the removal
            }
        }

        // If the discount was not found
        System.out.println("Discount not found. No changes were made.");
    }

    private void modifyDiscount() {
        System.out.println("Enter the discount name to modify:");
        String discountNameToModify = sc.nextLine();

        // Iterate through the discounts ArrayList
        for (Discount discount : discounts) {
            // Check if the current discount name matches the one to modify
            if (discount.getDiscountName().equals(discountNameToModify)) {
                System.out.println("Enter the new percentage:");
                int newPercentage = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                // Update the percentage of the discount
                discount.setDiscountPercentage(newPercentage);
                System.out.println("Discount percentage modified successfully.");
                return; // Exit the method after modifying the discount
            }
        }

        // If the discount was not found
        System.out.println("Discount not found. No changes were made.");
    }






    // SETTING SPECIAL DEALS
    public void setSpecialDeal(String name, double percentage) {


        SpecialDeal newSpecialDeal = new SpecialDeal(name, percentage);
        specialDeal.put(newSpecialDeal, name);

        System.out.println("Special Deal added successfully.");
    }



    // VISITOR STATISTICS
    public void viewVisitorStats() {
        System.out.println("Visitor Statistics:\n" +
                "        - Total Visitors: 1200\n" +
                "                - Total Revenue: $15,000\n" +
                "                - Most Popular Attraction: Jungle Safari");
    }

    // FEEDBACK
    public void viewFeedback() {
        // Check if the feedback map is empty
        if (feedback.isEmpty()) {
            System.out.println("No feedback to show.");
        } else {
            System.out.println("Visitor Feedback:");
            for (Map.Entry<String, String> entry : feedback.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }








}
