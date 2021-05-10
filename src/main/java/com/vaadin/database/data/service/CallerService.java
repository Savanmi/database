package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.repository.CallerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallerService {

    private CallerRepository callerRepository;

    public CallerService(CallerRepository callerRepository){
        this.callerRepository = callerRepository;
    }

    public List<Callers> findAll() {
        return (List<Callers>) callerRepository.findAll();
    }

    public long count() {
        return callerRepository.count();
    }

    public void delete(Callers callers) {
        callerRepository.delete(callers);
    }

    public void save(Callers caller) {
        if (caller == null) {
            System.out.println("NULL CALLER WHILE SAVE");
            return;
        }
        callerRepository.save(caller);
    }

}
