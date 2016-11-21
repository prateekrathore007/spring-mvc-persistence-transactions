package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "USER")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "TITLE")
    private String title;

    @ManyToMany(mappedBy = "userList", fetch = FetchType.EAGER)
    private List<Phone> phones;

    @Embedded
    private Address address;


    /**
     * default instructor
     */
    public User() {

    }

    /**
     * User constructor
     *
     * @param firstname
     * @param lastname
     * @param title
     * @param street
     * @param city
     * @param state
     * @param zip
     */
    public User(String firstname, String lastname, String title, String street, String city, String state, String zip) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = new Address(street, city, state, zip);
        this.title = title;
    }

    /**
     * Get ID of phone
     *
     * @return ID as int
     */
    public int getId() {
        return id;
    }

    /**
     * Set ID of user
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get firstname of user
     *
     * @return firstname as stirng
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set name of User
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Get lastname of User
     *
     * @return lastname as string
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set lastname of User
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get title of User
     *
     * @return title as string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of User
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get list of phones assigned to User
     *
     * @return List of phones
     */
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * Set List of phones for User
     *
     * @param phones
     */
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    /**
     * Get address of user
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set address of User
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Get User as string
     *
     * @return user as string
     */
    @Override
    public String toString() {
        return "User " +
                " [" +
                "id=" + id + "," +
                " firstName= " + firstname +
                ", lastName= " + lastname +
                ", address= " + address +
                ", title= " + title +
                "]";
    }
}