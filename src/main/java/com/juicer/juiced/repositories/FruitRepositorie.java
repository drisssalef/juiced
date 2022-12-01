package com.juicer.juiced.repositories;

import com.juicer.juiced.classes.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepositorie extends JpaRepository<Fruit,Integer> {
}
