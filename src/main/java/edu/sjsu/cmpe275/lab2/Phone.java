package edu.sjsu.cmpe275.lab2;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PHONE")
public class Phone {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private int id;

    @Column(name = "NUMB", unique = true)
    private String numb;

    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private Address address;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_PHONE",
            joinColumns = @JoinColumn(name = "phone_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))

    private List<User> userList;

    /**
     * Get list of users who have this phone
     *
     * @return List of users
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Set list of users
     *
     * @param userList
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * Phone constructor
     *
     * @param number
     * @param description
     * @param street
     * @param city
     * @param state
     * @param zip
     */
    public Phone(String number, String description, String street, String city, String state, String zip) {
        this.numb = number;
        this.description = description;
        this.address = new Address(street, city, state, zip);
    }

    /**
     * Phone default constructor
     */
    public Phone() {
    }

    /**
     * Get Number as string
     *
     * @return Number as string
     */
    public String getNumb() {
        return numb;
    }

    /**
     * Set phone number
     *
     * @param number
     */
    public void setNumb(String number) {
        this.numb = number;
    }

    /**
     * Get description of phone
     *
     * @return description as string
     */
    public String getDescription() {
        return description;
    }

    /**
     * set phone description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * get address of phone
     *
     * @return address class
     */
    public Address getAddress() {
        return address;
    }

    /**
     * set address of phone
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * get ID of phone
     *
     * @return id as int
     */
    public int getId() {
        return id;
    }

    /**
     * set ID of phone
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
