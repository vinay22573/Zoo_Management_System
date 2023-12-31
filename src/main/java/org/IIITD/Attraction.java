package org.IIITD;
public class Attraction {
    private String attractionId;
    private String attractionName;
    private String description;
    private double TicketPrice;
    // Other necessary fields and methods for managing an Attraction

    public Attraction(String attractionId, String attractionName, String description) {
        this.attractionId = attractionId;
        this.attractionName = attractionName;
        this.description = description;
    }

    public double getTicketPrice() {
        return TicketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        TicketPrice = ticketPrice;
    }

    public String getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(String attractionId) {
        this.attractionId = attractionId;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public void setAttractionName(String attractionName) {
        this.attractionName = attractionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
