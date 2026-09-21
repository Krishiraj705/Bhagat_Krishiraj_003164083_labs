package lab.pkg1.swing.ui;

public class Address {
    private String streetName;
    private String unitNum;
    private String city;
    private String zipCode;

    public Address(String streetName, String unitNum, String city, String zipCode) {
        this.streetName = streetName;
        this.unitNum = unitNum;
        this.city = city;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return streetName + ", Unit " + unitNum + ", " + city + ", " + zipCode;
    }
}