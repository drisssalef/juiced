package com.juicer.juiced.controllers;

import com.juicer.juiced.classes.Juice;
import com.juicer.juiced.repositories.JuiceRepositorie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/juice")
public class JuiceConroller {
    @Autowired
    private JuiceRepositorie juiceRepositorie;

    @GetMapping
    public List<Juice> list(){
        return juiceRepositorie.findByVisibleJuice(true);
    }

    @GetMapping
    @RequestMapping("{id}")
    public Juice get(@PathVariable int id){
        return juiceRepositorie.getOne(id);
    }

    @PostMapping
    public Juice create(@RequestBody final Juice juice){
        return juiceRepositorie.saveAndFlush(juice);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable int id){
        juiceRepositorie.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Juice update(@PathVariable int id,@RequestBody Juice juice){
        Juice oldjuice = juiceRepositorie.getOne(id);
        BeanUtils.copyProperties(juice,oldjuice,"juice_id");
        return juiceRepositorie.saveAndFlush(oldjuice);
    }

}
