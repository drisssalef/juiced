package com.juicer.juiced.services;

import com.juicer.juiced.classes.Fruit;
import com.juicer.juiced.classes.Juice;
import com.juicer.juiced.classes.Orders;
import com.juicer.juiced.repositories.FruitRepositorie;
import com.juicer.juiced.repositories.JuiceRepositorie;
import com.juicer.juiced.repositories.OrdersRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /*@Autowired
    OrderService orderService;*/

    @GetMapping("/")
    public String  fruitTabel(Model model){

        List<Fruit> f = fruitRepositorie.findAll();
        System.out.println(f);

        model.addAttribute("fruits",f);

        List<Juice> visibleJuice = new ArrayList<>();
        for (Juice j : juiceRepositorie.findAll()){

            if (j.getVisible_juice() == true){
                visibleJuice.add(j);
            }
        }
        model.addAttribute("juices" ,visibleJuice);
        //model.addAttribute("orderService", orderService);


        return "helloworld";
    }
    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute String f, BindingResult errors, Model model) {
        this.fruitTabel(model);
        Juice j = new Juice();

        model.addAttribute("f_id",f);
        return "helloworld.html";
    }
}
