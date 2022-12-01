package com.juicer.juiced.repositories;

import com.juicer.juiced.classes.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepositorie extends JpaRepository<Orders,Integer> {
}
