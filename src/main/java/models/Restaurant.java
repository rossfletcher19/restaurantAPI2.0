package models;


import enums.DiningStyle;

public class Restaurant {

    private String name;
    private String address;
    private String zipcode;
    private String phone;
    private String website;
    private String email;
    private String image;
    private int id;
    private DiningStyle diningStyle;

    public Restaurant(String name, String address, String zipcode, String phone, DiningStyle diningStyle) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = "no website listed";
        this.email = "no email available";
        this.image = "/resources/images/uploads/no_image.jpg"; //ignore me for now
        this.diningStyle = diningStyle;

    }

    public Restaurant(String name, String address, String zipcode, String phone, String website, String email, String image,  DiningStyle diningStyle) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.image = image;
        this.diningStyle = diningStyle;
   }

    public String getDiningStyle(){
        String message;
        switch (this.diningStyle){
            case CASUAL:
                message =  String.format("%s likes to keep things pretty casual. No stuffy suits here!", this.getName());
            break;

            case FASTFOOD:
               message = String.format("%s is a fast food restaurant. Don't expect ambiance.", this.getName());
                break;

            case FOODCART:
                message =  String.format("%s is part of the Portland Foodcart scene. Bring an umbrella if its raining!", this.getName());
                break;

            case FINEDINING:
                message =  String.format("Get ready to spend some coin at %s. Don't arrive underdressed - it's posh.", this.getName());
                break;

           default:
               message =  ("Something went wrong. Please try again.");
               break;
        }
        return message;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!zipcode.equals(that.zipcode)) return false;
        if (!phone.equals(that.phone)) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return image != null ? image.equals(that.image) : that.image == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
