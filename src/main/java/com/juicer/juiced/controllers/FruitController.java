package com.juicer.juiced.controllers;

import com.juicer.juiced.classes.Fruit;
import com.juicer.juiced.repositories.FruitRepositorie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fruit")
public class FruitController {

    @Autowired
    private FruitRepositorie fruitRepositorie;

    @GetMapping
    public List<Fruit> fruitList(){
        return fruitRepositorie.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Fruit get(@PathVariable int id){
        return fruitRepositorie.getOne(id);
    }

    @PostMapping
    public Fruit create(@RequestBody final Fruit fruit){
        return fruitRepositorie.saveAndFlush(fruit);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        fruitRepositorie.deleteById(id);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public Fruit update(@PathVariable int id,@RequestBody Fruit fruit){
        Fruit oldFruit = fruitRepositorie.getOne(id);
        BeanUtils.copyProperties(fruit,oldFruit,"fruit_id");
        return fruitRepositorie.saveAndFlush(oldFruit);
    }

}
