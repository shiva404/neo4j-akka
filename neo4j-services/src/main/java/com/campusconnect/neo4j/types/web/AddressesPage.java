package com.campusconnect.neo4j.types.web;

import com.campusconnect.neo4j.types.neo4j.Address;

import java.util.List;

/**
 * Created by sn1 on 4/16/15.
 */
public class AddressesPage {
    private int size;
    private int offset;
    private List<Address> addresses;

    public AddressesPage() {

    }

    public AddressesPage(int size, int offset, List<Address> addresses) {

        this.size = size;
        this.offset = offset;
        this.addresses = addresses;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
