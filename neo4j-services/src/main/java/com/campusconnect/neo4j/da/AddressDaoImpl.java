package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AddressDao;
import com.campusconnect.neo4j.da.iface.AuditEventDao;
import com.campusconnect.neo4j.da.iface.UserDao;
import com.campusconnect.neo4j.da.utils.EventHelper;
import com.campusconnect.neo4j.da.utils.TargetHelper;
import com.campusconnect.neo4j.repositories.AddressRepository;
import com.campusconnect.neo4j.types.common.AuditEventType;
import com.campusconnect.neo4j.types.common.Target;
import com.campusconnect.neo4j.types.neo4j.Address;
import com.campusconnect.neo4j.types.neo4j.User;
import com.campusconnect.neo4j.types.web.Event;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sn1 on 4/16/15.
 */
public class AddressDaoImpl implements AddressDao {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AuditEventDao auditEventDao;
    @Autowired
    private UserDao userDao;

    public AddressDaoImpl() {
    }

    @Override
    public List<Address> getAddresses(String userId) {
        return addressRepository.getAddressForUser(userId);
    }

    @Override
    public Address getAddress(String addressId) {
        return addressRepository.findOne(Long.parseLong(addressId));
    }

    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public Address createAddress(Address address, @PartialCacheKey String userId) {
        return addressRepository.save(address);
    }

    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public Address updateAddress(Address address, @PartialCacheKey String userId) {

        Address updatedAddress = addressRepository.save(address);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Long currentTime = System.currentTimeMillis();

            Target target = TargetHelper.createUserTarget(userDao.getUser(userId));
            Event updatedAddressUserEvent = EventHelper.createPrivateEvent(AuditEventType.UPDATED_ADDRESS.toString(), target);
            auditEventDao.addEvent(userId, updatedAddressUserEvent);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedAddress;
    }

    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public void deleteAddress(String addressId, @PartialCacheKey String userId) {

        addressRepository.delete(Long.parseLong(addressId));
        Target target = TargetHelper.createUserTarget(userDao.getUser(userId));
        Event event = EventHelper.createPrivateEvent(AuditEventType.DELETED_ADDRESS.toString(), target);
        auditEventDao.addEvent(userId, event);
    }

    @Override
    public void deleteAddressOfUser(User user) {
        List<Address> addresses = addressRepository.getAddressForUser(user.getId());
        for (Address address : addresses)
            addressRepository.delete(address);
    }
}
