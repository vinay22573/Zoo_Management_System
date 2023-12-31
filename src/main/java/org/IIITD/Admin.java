package org.IIITD;

import java.util.Map;

public interface Admin {
    void manageAttractions();

    void manageAnimals();

    void scheduleEvents();

    void setDiscounts();

    void setSpecialDeal(Map<SpecialDeal, String> specialDeal);

    void viewVisitorStats();

    void viewFeedback();
}
