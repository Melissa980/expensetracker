package com.melissa.ExpenseTracker.controller;

import com.melissa.ExpenseTracker.dto.PaymentMethod;
import com.melissa.ExpenseTracker.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public String displayPaymentMethods(Model model) {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        model.addAttribute("paymentMethods", paymentMethods);
        model.addAttribute("paymentMethod", new PaymentMethod());
        return "payment_methods"; // Assuming there's a "payment_methods" view template to display payment methods
    }

    @GetMapping("addPaymentMethod")
    public String addPaymentMethodForm(Model model) {
        model.addAttribute("paymentMethod", new PaymentMethod());
        return "add_payment_method"; // Assuming there's a "add_payment_method" view template for adding a payment method
    }

    @PostMapping("addPaymentMethod")
    public String addPaymentMethod(@Valid PaymentMethod paymentMethod, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paymentMethod", paymentMethod);
            return "add_payment_method"; // Return to the "add_payment_method" view template with validation errors
        }
        paymentMethodService.addPaymentMethod(paymentMethod);
        return "redirect:/payment-methods"; // Redirect to the list of payment methods after successful addition
    }

    @GetMapping("editPaymentMethod/{paymentMethodId}")
    public String editPaymentMethodForm(@PathVariable int paymentMethodId, Model model) {
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId);
        model.addAttribute("paymentMethod", paymentMethod);
        return "edit_payment_method"; // Assuming there's a "edit_payment_method" view template for editing a payment method
    }

    @PostMapping("editPaymentMethod/{paymentMethodId}")
    public String editPaymentMethod(@PathVariable int paymentMethodId, @Valid PaymentMethod paymentMethod, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paymentMethod", paymentMethod);
            return "edit_payment_method"; // Return to the "edit_payment_method" view template with validation errors
        }
        paymentMethod.setPaymentMethodId(paymentMethodId);
        paymentMethodService.updatePaymentMethod(paymentMethod);
        return "redirect:/payment-methods"; // Redirect to the list of payment methods after successful update
    }

    @GetMapping("deletePaymentMethod/{paymentMethodId}")
    public String deletePaymentMethod(@PathVariable int paymentMethodId) {
        paymentMethodService.deletePaymentMethod(paymentMethodId);
        return "redirect:/payment-methods"; // Redirect to the list of payment methods after successful deletion
    }

    @GetMapping("viewPaymentMethod/{paymentMethodId}")
    public String viewPaymentMethodDetails(@PathVariable int paymentMethodId, Model model) {
        // Get the payment method details from the service or DAO layer based on the paymentMethodId
        PaymentMethod paymentMethod = paymentMethodService.getPaymentMethodById(paymentMethodId);

        // Add the payment method object to the model to be used by Thymeleaf in the template
        model.addAttribute("paymentMethod", paymentMethod);

        // Return the name of the Thymeleaf template to render (in this case, "view_payment_method.html")
        return "view_payment_method";
    }
}
