package com.braylon.Braylon.service;

import com.braylon.Braylon.entities.State;
import com.braylon.Braylon.repositories.StateRepo;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Amir Jamal
 */
@Service
public class StateService {
    
    @Autowired
    private StateRepo staterepo;
    
    public Collection<State> findAll() {
        return this.staterepo.findAll();
    }
    
    public Optional<State> findById(int id) {
        return this.staterepo.findById(id);
    }
    
    
}
