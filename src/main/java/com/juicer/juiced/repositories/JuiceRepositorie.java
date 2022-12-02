package com.juicer.juiced.repositories;

import com.juicer.juiced.classes.Juice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuiceRepositorie extends JpaRepository<Juice,Integer> {
    List<Juice> findByVisibleJuice(Boolean visibleJuice);

}
