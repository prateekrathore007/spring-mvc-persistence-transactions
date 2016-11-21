package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.Phone;
import edu.sjsu.cmpe275.lab2.dao.PhoneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneManagerImpl implements PhoneManager {

    @Autowired
    PhoneDao phoneDao;

    /**
     * Get list of phones with given ID
     * @param id
     * @return list of phones
     */
    public List<Phone> getPhones(int id) {
        return phoneDao.getPhones(id);
    }

    /**
     * Add a phone to database
     * @param phone
     */
    public void addPhone(Phone phone) {
        phoneDao.addPhone(phone);
    }

    /**
     * Get the next ID from database
     * @return ID as String
     */
    public String getNextID() {
        return phoneDao.getNextID();
    }

    /**
     * Delete phone with given ID
     * @param id
     * @return 1 if delete successful, otherwise 0
     */
    public int deletePhone(int id) {
        return phoneDao.deletePhone(id);
    }

    /**
     * Update phone details
     * @param phone
     * @return 1 if update successful, otherwise 0
     */
    public int updatePhone(Phone phone) {
        return phoneDao.updatePhone(phone);
    }

    /**
     * Check is id is updatable with given number
     * @param id
     * @param numb
     * @return boolean true or false
     */
    public boolean iDIsUpdatableWithNumber(int id, String numb) {
        return phoneDao.iDIsUpdatableWithNumber(id, numb);
    }

}
