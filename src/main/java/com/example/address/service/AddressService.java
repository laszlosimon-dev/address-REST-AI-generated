package com.example.address.service;

import com.example.address.model.Address;
import com.example.address.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository addressRepository;

    public Address save(Address address) {
        log.info("Saving address for city: {}", address.getCity());
        return addressRepository.save(address);
    }

    public Optional<Address> findById(String id) {
        log.info("Looking up address by id: {}", id);
        return addressRepository.findById(id);
    }
}
