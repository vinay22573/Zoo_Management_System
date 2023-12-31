package org.IIITD;
public class Animal {

    private String animalName;
    private int animalType;
    // because animal are of three types and basically for a user its number typing would be easy than typing the string name of the mammal

    // Other necessary fields and methods for managing an Animal
    public Animal( String animalName, int animalType) {

        this.animalName = animalName;
        this.animalType = animalType;
    }
    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalType() {
        while (true) {
            switch (this.animalType) {
                case 1:
                    return "Mammal";
                case 2:
                    return "Amphibian";
                case 3:
                    return "Reptile";
                default:
                    System.out.println("Invalid input. Enter a valid Animal type:");
                    System.out.println(" Mammals      ---> 1");
                    System.out.println(" Amphibians   ---> 2");
                    System.out.println(" Reptiles     ---> 3");
            }
        }
    }
    public void setAnimalType(int animalType) {
        this.animalType = animalType;
    }
}
