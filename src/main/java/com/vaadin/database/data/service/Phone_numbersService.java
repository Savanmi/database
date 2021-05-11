package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Connection_prices;
import com.vaadin.database.data.entity.Phone_numbers;
import com.vaadin.database.data.repository.Phone_numbersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Phone_numbersService {

    private Phone_numbersRepository phone_numbersRepository;

    public Phone_numbersService(Phone_numbersRepository phone_numbersRepository){
        this.phone_numbersRepository = phone_numbersRepository;
    }

    public List<Phone_numbers> findAll() {
        return (List<Phone_numbers>) phone_numbersRepository.findAll();
    }

    public long count() {
        return phone_numbersRepository.count();
    }

    public void delete(Phone_numbers phone_numbers) {
        phone_numbersRepository.delete(phone_numbers);
    }

    public void save(Phone_numbers phone_numbers) {
        if (phone_numbers == null) {
            System.out.println("You try to save null object");
            return;
        }
        phone_numbersRepository.save(phone_numbers);

    }
}
