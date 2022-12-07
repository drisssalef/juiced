package com.juicer.juiced.controllers;

import com.juicer.juiced.services.StripeService;
import com.stripe.model.Coupon;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@Controller
public class PaymentController {
    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    /*@GetMapping("/")
    public String homepage() {
        return "homepage";
    }*/
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Don't do this in production, use a proper list  of allowed origins
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    /*========== REST APIs for Handling Payments ===================*/


    @PostMapping("/create-charge")
    public @ResponseBody
    ResponseEntity<String> createCharge(String email, String token, int amount) {
        //validate data
        if (token == null) {

            return ResponseEntity.internalServerError().body("Stripe payment token is missing. Please, try again later.");

        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, amount*100);
        if (chargeId == null) {
            return ResponseEntity.internalServerError().body("An error occurred while trying to create a charge.");
        }

        // You may want to store the charge id along with order information


        return ResponseEntity.ok("Success! Your charge id is " + chargeId);
    }

}
