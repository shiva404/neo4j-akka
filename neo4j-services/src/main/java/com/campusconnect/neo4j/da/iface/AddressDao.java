package com.campusconnect.neo4j.da.iface;

import com.campusconnect.neo4j.types.Address;

import java.util.List;

/**
 * Created by sn1 on 4/16/15.
 */
public interface AddressDao {

    List<Address> getAddresses(String userId);

    Address getAddress(String addressId);

    Address createAddress(Address address, String userId);

    Address updateAddress(Address address, String userId);

    void deleteAddress(String addressId, String userId);
}
