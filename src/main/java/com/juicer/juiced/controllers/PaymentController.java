package com.juicer.juiced.controllers;

import com.juicer.juiced.services.StripeService;
import com.stripe.model.Coupon;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PaymentController {
    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }

    @GetMapping("/subscription")
    public String subscriptionPage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "subscription";
    }

    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    /*========== REST APIs for Handling Payments ===================*/

    @PostMapping("/create-subscription")
    public @ResponseBody
    Response createSubscription(String email, String token, String plan, String coupon) {
        //validate data
        if (token == null || plan.isEmpty()) {
            Response res = new Response();
            res.setMessage("Stripe payment token is missing. Please, try again later.");
            res.setError();
            return res;
        }

        //create customer first
        String customerId = stripeService.createCustomer(email, token);

        if (customerId == null) {
            Response res = new Response();
            res.setMessage("An error occurred while trying to create a customer.");
            res.setError();
            return res;
        }

        //create subscription
        String subscriptionId = stripeService.createSubscription(customerId, plan, coupon);
        if (subscriptionId == null) {
            Response res = new Response();
            res.setMessage("An error occurred while trying to create a subscription.");
            res.setError();
            return res;
        }

        // Ideally you should store customerId and subscriptionId along with customer object here.
        // These values are required to update or cancel the subscription at a later stage.

        Response res = new Response();
        res.setMessage("Success! Your subscription id is " + subscriptionId);
        return res;

    }

    @PostMapping("/cancel-subscription")
    public @ResponseBody
    Response cancelSubscription(String subscriptionId) {
        boolean status = stripeService.cancelSubscription(subscriptionId);
        if (!status) {
            Response res = new Response();
            res.setMessage("Failed to cancel the subscription. Please, try later.");
            res.setError();
            return res;
        }

        Response res = new Response();
        res.setMessage("Subscription cancelled successfully.");
        return res;
    }

    @PostMapping("/coupon-validator")
    public @ResponseBody
    Response couponValidator(String code) {
        Coupon coupon = stripeService.retrieveCoupon(code);
        if (coupon != null && coupon.getValid()) {
            String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100) : coupon.getPercentOff() + "%") +
                    " OFF " + coupon.getDuration();

            Response res = new Response();
            res.setMessage(details);
            return res;

        } else {

            Response res = new Response();
            res.setMessage("This coupon code is not available. This may be because it has expired or has "+  "already been applied to your account.");
            res.setError();
            return res;
        }
    }

    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token) {
        //validate data
        if (token == null) {
            Response res = new Response();
            res.setMessage( "Stripe payment token is missing. Please, try again later.");
            res.setError();
            return res;

        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
        if (chargeId == null) {
            Response res = new Response();
            res.setMessage( "An error occurred while trying to create a charge.");
            res.setError();
            return res;
        }

        // You may want to store the charge id along with order information

        Response res = new Response();
        res.setMessage("Success! Your charge id is " + chargeId);
        return res;
    }

}
