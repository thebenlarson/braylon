package com.braylon.Braylon.repositories;

import com.braylon.Braylon.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Amir Jamal
 */

public interface StateRepo extends JpaRepository<State, Integer>{
    
}
