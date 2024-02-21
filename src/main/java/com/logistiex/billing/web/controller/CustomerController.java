package com.logistiex.billing.web.controller;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.web.mvc.controller.BaseCrudController;
import com.logistiex.billing.data.model.Customer;
import com.logistiex.billing.service.dto.CustomerDTO;
import com.logistiex.billing.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController extends BaseCrudController<String, CustomerDTO, Customer> {
    private final CustomerService customerService;
    public CustomerController(CustomerService service, BaseRepository<Customer, String> repository) {
        super(service, repository);
        this.customerService = service;
    }


}
