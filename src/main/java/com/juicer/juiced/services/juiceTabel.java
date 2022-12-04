package com.juicer.juiced.services;

import com.juicer.juiced.classes.Fruit;
import com.juicer.juiced.classes.Juice;
import com.juicer.juiced.classes.Orders;
import com.juicer.juiced.repositories.FruitRepositorie;
import com.juicer.juiced.repositories.JuiceRepositorie;
import com.juicer.juiced.repositories.OrdersRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class juiceTabel {
    @Autowired
    private FruitRepositorie fruitRepositorie;
    @Autowired
    private JuiceRepositorie juiceRepositorie;

    @Autowired
    OrdersRepositorie ordersRepositorie;

    @GetMapping("/")
    public String  fruitTabel(Model model){
        List<Fruit> f = fruitRepositorie.findAll();
        model.addAttribute("fruits",f);

        List<Juice> visibleJuice = new ArrayList<>();
        for (Juice j : juiceRepositorie.findAll()){

            if (j.getVisible_juice() == true){
                visibleJuice.add(j);
            }
        }
        model.addAttribute("juices" ,visibleJuice);


        return "helloworld";
    }
}
