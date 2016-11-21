package edu.sjsu.cmpe275.lab2;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    /**
     * Constructor for address
     * @param street
     * @param city
     * @param state
     * @param zip
     */
    public Address (String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    /**
     * Default constructor
     */
    public Address() {}

    /**
     * Get address street
     * @return street String
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set address street
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get address city
     * @return city string
     */
    public String getCity() {
        return city;
    }

    /**
     * Set address city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get address state
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Set address state
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Get address zip
     * @return string zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * Set address zip
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * returns address as string
     * @return address as string
     */
    public String toString() {
        return "Address street: " + getStreet() +
                ", city: " + getCity() +
                ", state: " + getState() +
                ", zip: " + getZip();
    }
}
