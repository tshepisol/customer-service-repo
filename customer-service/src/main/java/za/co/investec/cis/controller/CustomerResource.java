package za.co.investec.cis.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.domain.CustomerSearchDTO;
import za.co.investec.cis.error.CustomerErrorType;
import za.co.investec.cis.service.CustomerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/customer")
public class CustomerResource {

    private static  final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    @Autowired
    protected CustomerService customerService;


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long customerId){

        log.info("retrieve customer id: {}", customerId);

        Optional<Customer> invoiceOptional = customerService.get(customerId);

       if(invoiceOptional.isPresent()){
           return new ResponseEntity<>(invoiceOptional.get(), HttpStatus.OK);
       }

        log.warn("Customer with id [] not found", customerId);

        return new ResponseEntity<>(new CustomerErrorType("Customer with id "+customerId+" not found"), HttpStatus.NOT_FOUND);
    }


    @GetMapping
    public ResponseEntity<?> search(@RequestParam(name = "name") String  name, @RequestParam(name = "id") String  idNumber, @RequestParam(name = "phone") String  phone){

        log.info("retrieve customer name: {} id:{} phone: {}", name, idNumber, phone);

        List<Customer> customers = customerService.search(new CustomerSearchDTO(name, idNumber, phone));

        if(!customers.isEmpty()){
            return new ResponseEntity<>(customers, HttpStatus.NOT_FOUND);
        }

        log.info("Customers {} found", customers);

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer, UriComponentsBuilder uriComponentsBuilder){

        log.info("creating customer: {}", customer);

        if(customer.getId() != null && customerService.get(customer.getId()).isPresent()){
            log.warn("unable to add customer. customer already exist: {}", customer);
            return new ResponseEntity<>(new CustomerErrorType("Unable to create. Customer exist: "+ customer), HttpStatus.BAD_REQUEST);
        }

        customer = customerService.save(customer);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriComponentsBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri());

        return new ResponseEntity<String>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Customer customer){

        log.info("update customer: {}", customer);

        customer = customerService.update(customer);

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long customerId){

        log.info("delete customer id: {}", customerId);

        customerService.delete(customerId);

        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
