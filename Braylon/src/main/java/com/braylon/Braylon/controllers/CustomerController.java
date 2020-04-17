package com.braylon.Braylon.controllers;

import com.braylon.Braylon.entities.Customer;
import com.braylon.Braylon.entities.CustomerOrder;
import com.braylon.Braylon.entities.State;
import com.braylon.Braylon.service.CustomerService;
import com.braylon.Braylon.service.StateService;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Amir Jamal
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StateService stateService;

    /**
     *
     * shows the customer list and create customer form
     */
    @GetMapping("customers")
    public String showCustomerList(@AuthenticationPrincipal UserDetails user, Model model) {
        
        //getting the string id of a user and converting it to a number
        int userId = Integer.parseInt(user.getUsername());
        
        // Get the customer for each particular user
        Collection<Customer> customers = customerService.findByUser_Id(userId);
        Collection<State> states = stateService.findAll();

        // Add customers to the model
        model.addAttribute("customers", customers);

        // creating a new customer 
        // if model does not have a single customer
        // create a new empty customer (blank fields)
        if (!model.containsAttribute("customer")) {

            //filler space, fills when it's empty
            model.addAttribute("customer", new Customer());
        }

        model.addAttribute("states", states);

        // Return the view
        return "customerInfo/index"; //spoke with Amir and changed this

    }

    /**
     *
     * creating a new customer this method can redirect user to the
     * "showCustomerList" method
     */
    @PostMapping("customers")
    public String addCustomer(@Valid Customer customer, BindingResult result, RedirectAttributes redirAttr) {

        // for validation BindingResult (erros in validation)
        if (result.hasErrors()) {

            // Add customer to the model 
            redirAttr.addFlashAttribute("org.springframework.validation.BindingResult.customer", result);
            redirAttr.addFlashAttribute("customer", customer);

            // Redirect
            return "redirect:/customers";
        }

        this.customerService.save(customer);

        // Flag 'created' to true so a message appears on the view
        redirAttr.addFlashAttribute("created", true);

        return "redirect:/index"; //spoke with Amir and changed this

    }

    /**
     *
     * editing a existing customer
     *
     */
    @GetMapping("customer/{id}/edit")
    public String showEditForm(@PathVariable("id") int id, Model model) {

        // Find customer by id
        Optional<Customer> editCustomer = this.customerService.findById(id);

        // If the customer exists
        if (editCustomer.isPresent()) {
            // Get the customer object
            Customer customer = editCustomer.get();
            Collection<State> states = stateService.findAll();
            
            //list of customer orders
            List<CustomerOrder> customerOrders = customer.getCustomerOrders(); 
            
            //check if list has one item or is not empty 
            if (!customerOrders.isEmpty()){
                CustomerOrder customerOrder = customerOrders.get(customerOrders.size()-1);
                
                model.addAttribute("lastCustomerOrder", customerOrder);

            }
            
            if (!model.containsAttribute("customer")) {

                // Add the customer to the model
                model.addAttribute("customer", customer);

            }
            
            model.addAttribute("state", states);

            // Show the edit form
            return "customer.html";
        }

        // Redirect to the view
        return "redirect:/customers";
    }
    
    
    /*
    *
    * update and save information for a customer
    *
    */
    @PostMapping("customer/{id}/update")
    public String updateCustomer(@PathVariable("id") int id, @Valid Customer customer, BindingResult result, RedirectAttributes redirAttr) {
        
        //based on the url id of the customer
        customer.setCustomer_id(id);
        
        // for validation BindingResult (erros in validation)
        if (result.hasErrors()) {

            // Add customer to the model  (if there are errors in validation)
            redirAttr.addFlashAttribute("org.springframework.validation.BindingResult.customer", result);
            redirAttr.addFlashAttribute("customer", customer);

            // Redirect
            return "redirect:/customer/" + id + "/edit";
        }

        this.customerService.save(customer);

        // view appears with 'updated' message 
        redirAttr.addFlashAttribute("updated", true);

        return "redirect:/customers";

    }
    
    @GetMapping("customer/{id}/delete")
    public String deleteCustomer(@PathVariable("id") int id, RedirectAttributes redirAttr) {
        
        //calls the service to delete the customer by id
        this.customerService.deleteById(id);
        
        //view appears with 'deleted' message
        redirAttr.addFlashAttribute("deleted", true);
        
        // Redirect to the view
        return "redirect:/customers";
    }
    
    
    

}
