package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Address;
import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private AddressRepository addressRepository;

    public AddressService (AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    public long count() {
        return addressRepository.count();
    }

    public void delete(Address address) {
        addressRepository.delete(address);
    }

    public void save(Address address) {
        if (address == null) {
            System.out.println("You try to save null object");
            return;
        }
        addressRepository.save(address);

    }
}
