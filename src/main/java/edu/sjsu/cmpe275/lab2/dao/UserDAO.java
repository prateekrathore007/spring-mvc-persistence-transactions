package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daffy on 11/7/16.
 */
@Repository
@Transactional(readOnly = false)
public class UserDAO implements IUserDAO {

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Get a user based on ID given
     *
     * @param userId
     * @return List which has only one element
     */
    public List<User> getUserDetails(int userId) {
        String query = "Select u From User u WHERE u.id = :arg1";
        Query q = entityManager.createQuery(query, User.class);
        q.setParameter("arg1", userId);
        List<User> user = q.getResultList();
        return user;
    }


    public List<Integer> getAllUsersIDs() {
        List<Integer> idList = new ArrayList<Integer>();
        String query = "Select u From User u";
        Query q = entityManager.createQuery(query, User.class);
        List<User> user = q.getResultList();
        for (int i = 0; i < user.size(); i++) {
            idList.add(user.get(i).getId());
        }
        return idList;
    }


    /**
     * Save a user to database
     *
     * @param newUser
     * @return The User ID of the newly created user.
     */
    public int saveUserDetails(User newUser) {
        newUser = entityManager.merge(newUser);
        entityManager.flush(); //forces the data to persist in the database immediately
        return newUser.getId();
    }

    /**
     * Delete a user of given ID
     *
     * @param userId
     * @return The status of the execute commmand. 1 is Success, 0 if false.
     */
    public int deleteUser(int userId) {
        String query1 = "DELETE FROM USER_PHONE WHERE user_id=?1";
        int ret = 0;
        try {
            Query q = entityManager.createNativeQuery(query1);
            q.setParameter(1, userId);
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        String query = "DELETE User where id=?1";
        try {
            Query q = entityManager.createQuery(query);
            q.setParameter(1, userId);
            ret = q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return ret;
    }

    /**
     * Gives the next ID from database
     *
     * @return ID as string
     */
    public String getNextID() {
        String db = "cmpe275_lab2";
        String table = "USER";
        String query = "SELECT t.AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES t WHERE t.TABLE_SCHEMA = ?1 and t.TABLE_NAME = ?2";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter(1, db);
        q.setParameter(2, table);
        return q.getSingleResult().toString();
    }

    /**
     * Update user details
     *
     * @param user
     * @return The status of the execute commmand. 1 is Success, 0 if false.
     */
    public int updateUser(User user) {
        String query = "UPDATE User u SET u.firstname=?1, u.lastname=?2, u.address.street=?3, u.address.city=?4, u.address.state=?5, u.address.zip=?6, u.title=?7 WHERE u.id=?8";
        Query q = entityManager.createQuery(query);
        q.setParameter(1, user.getFirstname());
        q.setParameter(2, user.getLastname());
        q.setParameter(3, user.getAddress().getStreet());
        q.setParameter(4, user.getAddress().getCity());
        q.setParameter(5, user.getAddress().getState());
        q.setParameter(6, user.getAddress().getZip());
        q.setParameter(7, user.getTitle());
        q.setParameter(8, user.getId());
        int ret = q.executeUpdate();
        return ret;
    }
}
