package com.vaadin.database.data.service;

import com.vaadin.database.data.entity.Callers;
import com.vaadin.database.data.repository.CallerRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallerService {

    private CallerRepository callerRepository;

    public List<Object[]> findCallersList(Integer texID, boolean id_deadhead, Integer lowAge, Integer upAge, String second ){
        return callerRepository.findCallersList(texID,id_deadhead,lowAge,upAge,second);
    }

    public List<Object[]> findDebtorsListbyLDCDebt(Integer integ){
        return  callerRepository.findDebtorsListbyLDCDebt(integ);
    };

    public List<Object[]> findDebtorsListbySubscriptionDebtAge(Integer integ){
        return  callerRepository.findDebtorsListbySubscriptionDebtAge(integ);
    };

    public List<Object[]> findDebtorsListbyLDCDebtAge(Integer integ){
        return  callerRepository.findDebtorsListbyLDCDebtAge(integ);
    };

    public List<Object[]> findDebtorsListbySubscriptionDebt(Integer integ){
        return  callerRepository.findDebtorsListbySubscriptionDebt(integ);
    };


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
