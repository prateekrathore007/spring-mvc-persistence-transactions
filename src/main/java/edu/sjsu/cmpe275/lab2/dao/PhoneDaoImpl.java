package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.Phone;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class PhoneDaoImpl implements PhoneDao {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Get list of phones with given ID
     * @param id
     * @return list of phones
     */
    public List<Phone> getPhones(int id) {
        String query = "Select p From Phone p WHERE p.id = :arg1";
        Query q = manager.createQuery(query, Phone.class);
        q.setParameter("arg1", id);
        List<Phone> phones = q.getResultList();
        return phones;
    }

    /**
     * Add a phone to database
     * @param phone
     */
    public void addPhone(Phone phone) {
        manager.persist(phone);
    }

    /**
     * Get the next ID from database
     * @return ID as String
     */
    public String getNextID() {
        String db = "cmpe275_lab2";
        String table = "PHONE";
        String query = "SELECT t.AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES t WHERE t.TABLE_SCHEMA = ?1 and t.TABLE_NAME = ?2";
        Query q = manager.createNativeQuery(query);
        q.setParameter(1,db);
        q.setParameter(2,table);
        return q.getSingleResult().toString();
    }

    /**
     * Delete phone with given ID
     * @param id
     * @return 1 if delete successful, otherwise 0
     */
    public int deletePhone(int id) {
        String query = "DELETE Phone where id=?1";
        Query q = manager.createQuery(query);
        q.setParameter(1,id);
        int ret = q.executeUpdate();
        System.out.println(System.currentTimeMillis() + " DELETE: phoneid " + id + " deleted");
        return ret;
    }

    /**
     * Update phone details
     * @param phone
     * @return 1 if update successful, otherwise 0
     */
    public int updatePhone(Phone phone) {
        System.out.println("Entered updatePHone");
        int ret = 0;
        try {
            System.out.println("Entered try");
            manager.merge(phone);
            ret = 1;
            System.out.println("try finish");
        } catch (Exception e) {
            ret = 0;
            System.out.println("Exception in phone merge !");
            //System.out.println(e.printStackTrace()));
        }
        return ret;
    }

    /**
     * Check is id is updatable with given number
     * @param id
     * @param numb
     * @return boolean true or false
     */
    public boolean iDIsUpdatableWithNumber(int id, String numb) {
        String query = "Select p From Phone p WHERE p.id <> :arg1";
        Query q = manager.createQuery(query, Phone.class);
        q.setParameter("arg1",id);
        List<Phone> phones;
        try {
            phones = q.getResultList();
        } catch (PersistenceException pe) {
            return false;
        }
        for (Phone phone : phones) {
            if (numb.equals(phone.getNumb())) {
                return false;
            }
        }
        return true;
    }
}
