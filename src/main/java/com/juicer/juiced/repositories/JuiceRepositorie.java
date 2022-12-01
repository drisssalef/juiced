package com.juicer.juiced.repositories;

import com.juicer.juiced.classes.Juice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuiceRepositorie extends JpaRepository<Juice,Integer> {
}
