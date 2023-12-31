package org.IIITD;
public class SpecialDeal {
    private String discountName;
    private double discountPercentage;

    public SpecialDeal(String discountName, double discountPercentage) {
        this.discountName = discountName;
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
