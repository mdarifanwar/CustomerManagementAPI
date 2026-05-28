package com.example.customerdatasystem.controller;

import com.example.customerdatasystem.entity.Customer;
import com.example.customerdatasystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    // Login page
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout,
                       Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully!");
        }
        return "login";
    }
    
    // Dashboard page (after login)
    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        long customerCount = customerService.getCustomerCount();
        model.addAttribute("customerCount", customerCount);
        return "dashboard";
    }
    
    // Customer entry form page
    @GetMapping("/customer/new")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }
    
    // Save customer
    @PostMapping("/customer/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        // Check for validation errors
        if (result.hasErrors()) {
            return "customer-form";
        }
        
        // Check if email already exists (only for new customers or when email is changed)
        if (customer.getId() == null || !customerService.getCustomerById(customer.getId())
                .map(existingCustomer -> existingCustomer.getEmail().equals(customer.getEmail()))
                .orElse(false)) {
            if (customerService.existsByEmail(customer.getEmail())) {
                result.rejectValue("email", "error.customer", "Email already exists!");
                return "customer-form";
            }
        }
        
        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Customer '" + customer.getName() + "' has been saved successfully!");
            return "redirect:/customers";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving customer: " + e.getMessage());
            return "customer-form";
        }
    }
    
    // Display all customers
    @GetMapping("/customers")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }
    
    // View customer details
    @GetMapping("/customer/{id}")
    public String viewCustomer(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer-details";
    }
    
    // Edit customer form
    @GetMapping("/customer/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        model.addAttribute("customer", customer);
        return "customer-form";
    }
    
    // Delete customer
    @PostMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", 
                "Customer '" + customer.getName() + "' has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", 
                "An error occurred while deleting customer: " + e.getMessage());
        }
        return "redirect:/customers";
    }
}
