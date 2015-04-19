package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AddressDao;
import com.campusconnect.neo4j.repositories.AddressRepository;
import com.campusconnect.neo4j.types.Address;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.googlecode.ehcache.annotations.Property;
import com.googlecode.ehcache.annotations.TriggersRemove;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sn1 on 4/16/15.
 */
public class AddressDaoImpl implements AddressDao {
    @Autowired
    private AddressRepository addressRepository;

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
        return addressRepository.save(address);
    }
    
    @Override
    @TriggersRemove(cacheName = "userIdCache", keyGenerator = @KeyGenerator(name = "HashCodeCacheKeyGenerator", properties = @Property(name = "includeMethod", value = "false")))
    public void deleteAddress(String addressId, @PartialCacheKey String userId) {
        addressRepository.delete(Long.parseLong(addressId));
    }
}
