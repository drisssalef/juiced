package com.juicer.juiced.controllers;

import com.juicer.juiced.classes.Orders;
import com.juicer.juiced.repositories.OrdersRepositorie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrdersController {
    @Autowired
    private OrdersRepositorie ordersRepositorie;

    @GetMapping
    public List<Orders> list(){
        return ordersRepositorie.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Orders get(@PathVariable int id){
        return ordersRepositorie.getOne(id);
    }

    @PostMapping
    public Orders create(@RequestBody final Orders orders){
        return  ordersRepositorie.saveAndFlush(orders);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        ordersRepositorie.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Orders update(@PathVariable int id , @RequestBody Orders orders){
        Orders oldOrder = ordersRepositorie.getOne(id);
        BeanUtils.copyProperties(orders,oldOrder,"orders_id");
        return ordersRepositorie.saveAndFlush(oldOrder);
    }

}
