package com.example.assignment2_ponhvathvann_1502538;

public class Restaurant {
    private String name;
    private String address;
    private String contact;
    private String cuisine;
    private String averageCost;
    private String rating;
    private String ratingText;
    private String reviewCount;
    private String imageURL;

    public Restaurant(String name, String address, String contact, String cuisine,
                      String averageCost, String rating, String ratingText,
                      String reviewCount, String imageURL) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.cuisine = cuisine;
        this.averageCost = averageCost;
        this.rating = rating;
        this.ratingText = ratingText;
        this.reviewCount = reviewCount;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingText() {
        return ratingText;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", averageCost='" + averageCost + '\'' +
                ", rating='" + rating + '\'' +
                ", ratingText='" + ratingText + '\'' +
                ", reviewCount='" + reviewCount + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
