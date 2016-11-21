package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.Phone;
import java.util.List;

public interface PhoneDao {

    /**
     * Get list of phones with given ID
     * @param id
     * @return list of phones
     */
    public List<Phone> getPhones(int id);

    /**
     * Add a phone to database
     * @param phone
     */
    public void addPhone(Phone phone);

    /**
     * Get the next ID from database
     * @return ID as String
     */
    public String getNextID();

    /**
     * Delete phone with given ID
     * @param id
     * @return 1 if delete successful, otherwise 0
     */
    public int deletePhone(int id);

    /**
     * Update phone details
     * @param phone
     * @return 1 if update successful, otherwise 0
     */
    public int updatePhone(Phone phone);

    /**
     * Check is id is updatable with given number
     * @param id
     * @param numb
     * @return boolean true or false
     */
    public boolean iDIsUpdatableWithNumber(int id, String numb);
}