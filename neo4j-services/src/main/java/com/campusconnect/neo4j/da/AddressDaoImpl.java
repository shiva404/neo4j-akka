package com.campusconnect.neo4j.da;

import com.campusconnect.neo4j.da.iface.AddressDao;
import com.campusconnect.neo4j.repositories.AddressRepository;
import com.campusconnect.neo4j.types.Address;
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
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
    
    @Override
    public Address updateAddress(Address address) {
        return addressRepository.save(address);
        
    }
    
    @Override
    public void deleteAddress(String addressId) {
        addressRepository.delete(Long.parseLong(addressId));
        
    }
}
