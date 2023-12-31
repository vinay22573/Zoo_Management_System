package org.IIITD;
public class Discount extends SpecialDeal {

    private String discountCode;

    public Discount(String discountName, double discountPercentage) {
        super(discountName, discountPercentage);
        this.discountCode = generateDiscountCode(discountName, discountPercentage);
    }

    private String generateDiscountCode(String discountName, double discountPercentage) {
        // Assume that discount code is created by concatenating the uppercase discount name and the percentage
        String nameWithoutSpaces = discountName.replaceAll("\\s", "").toUpperCase();
        return nameWithoutSpaces + (int) discountPercentage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    public double getPercentage() {
        return super.getDiscountPercentage();
    }
}
