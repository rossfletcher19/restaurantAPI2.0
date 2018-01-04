package models;


public class Restaurant {

    private String name;
    private String address;
    private String zipcode;
    private String phone;
    private String website;
    private String email;
    private int id;

    public Restaurant(String name, String address, String zipcode, String phone) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = "no website listed";
        this.email = "no email available";
    }

    public Restaurant(String name, String address, String zipcode, String phone, String website, String email) {
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.website = website;
        this.email = email;
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

        if (!name.equals(that.name)) return false;
        if (!address.equals(that.address)) return false;
        if (!zipcode.equals(that.zipcode)) return false;
        if (!phone.equals(that.phone)) return false;
        if (website != null ? !website.equals(that.website) : that.website != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + zipcode.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
