package edu.sjsu.cmpe275.lab2;

import edu.sjsu.cmpe275.lab2.dao.IUserDAO;
import edu.sjsu.cmpe275.lab2.dao.PhoneDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@SuppressWarnings("deprecation")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:web/WEB-INF/spring-servlet.xml" })
public class AppTest {
    @Autowired(required = true)
    private IUserDAO userdao;
    @Autowired(required = true)
    private PhoneDao phondao;
    private User newuser;
    private Phone newphone;
    private static int userid = 1;
    int phoneid = 1;

    /**
     * Create a user and phone instance.
     */
    @Before
    public void setUp() {
        newuser = new User("Sadhana", "Dafle", "Software Engineer", "33S, 3rd street", "San Jose", "CA", "95113");
        newphone = new Phone("1111111111", "Home", "420S", "San Jose", "CA", "95126");
    }

    /**
     * Test Unit for Saving the user.
     */
    @Test
    public void test1UserSave() {
        userid = userdao.saveUserDetails(newuser);
        Assert.assertEquals(userdao.getUserDetails(userid).get(0).getFirstname(), newuser.getFirstname());
    }

    /**
     * Test Unit for Updating the user.
     */
    @Test
    public void test2UserUpdate() {
        newuser.setFirstname("Vikas");
        newuser.setTitle("MBA");
        int id = userdao.updateUser(newuser);
        Assert.assertEquals(newuser.getId(), id);
    }

    /**
     * Test Unit for Getting the User details
     */
    @Test
    public void test3UserGet() {
        List<User> userfetched = userdao.getUserDetails(userid);
        Assert.assertEquals(userfetched.size()>0,true);
    }

    /**
     * Test Unit for Deleting the user.
     */
    @Test
    public void test4UserDelete() {
        int status = userdao.deleteUser(userid);
        Assert.assertEquals(status, 1);
    }

    /**
     * Test Unit for Saving the phone.
     */
    @Test
    public void test5PhoneSave() {
        phondao.addPhone(newphone);
        Assert.assertEquals(phondao.getPhones(phoneid).get(0).getNumb(), newphone.getNumb());
    }

    /**
     * Test Unit for Updating the phone.
     */
    @Test
    public void test6PhoneUpdate() {
        newphone.setDescription("Cell");
        newphone.setNumb("9833731927");
        int status = phondao.updatePhone(newphone);
        Assert.assertEquals(status>0, true);
    }

    /**
     * Test Unit for Getting the phone.
     */
    @Test
    public void test7PhoneGet() {
        List<Phone> phonefetched = phondao.getPhones(phoneid);
        Assert.assertEquals(phonefetched.size()>=0,true);
    }

    /**
     * Test Unit for Deleting the phone.
     */
    @Test
    public void test7PhoneDelete() {
        int status = phondao.deletePhone(phoneid);
        Assert.assertEquals(status, 1);
    }
}