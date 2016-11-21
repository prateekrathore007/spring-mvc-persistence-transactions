package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.User;
import java.util.List;

public interface IUserDAO {

    /**
     * Get a user based on ID given
     *
     * @param userId
     * @return List which has only one element
     */
    public List<User> getUserDetails(int userId);

    /**
     * Save a user to database
     *
     * @param newUser
     * @return
     */
    public int saveUserDetails(User newUser);

    /**
     * Delete a user of given ID
     *
     * @param userId
     * @return
     */
    public int deleteUser(int userId);

    /**
     * Update user details
     *
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * Gives the next ID from database
     *
     * @return ID as string
     */
    public String getNextID();

    /**
     * Get all user Ids
     * @return list of user IDs
     */
    public List<Integer> getAllUsersIDs();
}
