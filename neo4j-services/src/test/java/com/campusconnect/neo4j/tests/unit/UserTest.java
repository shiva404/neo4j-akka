package com.campusconnect.neo4j.tests.unit;

import com.campusconnect.neo4j.da.UserDaoImpl;
import com.campusconnect.neo4j.da.iface.AddressDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.tests.TestBase;
import com.campusconnect.neo4j.types.Address;
import com.campusconnect.neo4j.types.AddressesPage;
import com.campusconnect.neo4j.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static com.campusconnect.neo4j.tests.functional.base.DataBrewer.getFakeAddress;
import static com.campusconnect.neo4j.tests.functional.base.DataBrewer.getFakeUser;
import static com.campusconnect.neo4j.tests.functional.base.DataBrewer.getFakeUserWithAddress;

/**
 * Created by sn1 on 1/18/15.
 */
public class UserTest extends TestBase {

    User createdUser;
    @Autowired
    private UserDao userDaoImpl;
    
    @Autowired
    private AddressDao addressDao;

    @Test
    public void createTest(){
        createdUser = userDaoImpl.createUser(getFakeUser(), null);
    }

    @Test(dependsOnMethods = "createTest")
    public void getUser(){
        User resultUser = userDaoImpl.getUser(createdUser.getId());
        System.err.println(resultUser);
    }

    @Test(dependsOnMethods = "getUser")
    public void updateUser(){
        User resultUser = userDaoImpl.getUser(createdUser.getId());

        System.out.println(resultUser);
        resultUser.setName("abc");
        userDaoImpl.updateUser(resultUser.getId(), resultUser);

        User updatedUser = userDaoImpl.getUser(createdUser.getId());
        updatedUser.getName();
    }

    @Test
    public void createUserWithAddress() {
        createdUser = userDaoImpl.createUser(getFakeUserWithAddress(), null);
        User resultUser = userDaoImpl.getUser(createdUser.getId());
        System.err.println(resultUser);
    }

    @Test
    public void addFollowerToFixedIdUser(){
        User fixedIdUser = userDaoImpl.getUser("8318f66b-c836-4c89-888b-97dc72927e78");
        User secondUser = userDaoImpl.createUser(getFakeUser(), null);
        userDaoImpl.createFollowingRelation(fixedIdUser, secondUser);
    }

    @Test
    public void addFollowingToFixedIdUser(){
        User fixedIdUser = userDaoImpl.getUser("8318f66b-c836-4c89-888b-97dc72927e78");
        User secondUser = userDaoImpl.createUser(getFakeUser(), null);
        userDaoImpl.createFollowingRelation(secondUser, fixedIdUser);
    }

    @Test(dependsOnMethods = "getUser")
    public void addFollower(){
        User secondUser = userDaoImpl.createUser(getFakeUser(), null);
        userDaoImpl.createFollowingRelation(createdUser, secondUser);
    }

    @Test(dependsOnMethods = "getUser")
    public void addFollowing(){
        User secondUser = userDaoImpl.createUser(getFakeUser(), null);
        userDaoImpl.createFollowingRelation(secondUser, createdUser);
    }

    @Test
    public void getFollowers(){
        List<User> users = userDaoImpl.getFollowers("8318f66b-c836-4c89-888b-97dc72927e78");
        System.out.println(users);
    }

    @Test
    public void getFollowing(){
        List<User> users = userDaoImpl.getFollowers("8318f66b-c836-4c89-888b-97dc72927e78");
        System.out.println(users);
    }
    
    @Test
    public void testUserAddressFlows() {
        User createdUser = userDaoImpl.createUser(getFakeUserWithAddress(), null);
        userDaoImpl.addAddressToUser(getFakeAddress("HOME"), createdUser);

        User user = userDaoImpl.getUser(createdUser.getId());
        
        Set<Address> addresses = user.getAddresses();
        System.out.println(addresses);
        assert addresses.size() == 2;
        
        List<Address> addressList = addressDao.getAddresses(createdUser.getId());
        assert addressList.size() == 2;
    }
}
