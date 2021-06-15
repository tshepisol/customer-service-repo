package za.co.investec.cis.controller;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.service.CustomerService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerResourceTest {

    Mockery mockery;
    CustomerService customerService;

    CustomerResource customerResource = new CustomerResource();


    @Before
    public void init(){
        mockery = new Mockery(){{

        }};

        customerService = mockery.mock(CustomerService.class);
        customerResource.customerService = customerService;

    }

    @Test
    public void getSingleCustomerTest() {

        mockery.checking( new Expectations(){{
            atLeast(1).of(customerService).get(1L);
             will(returnValue( Optional.ofNullable(createCustomer(1L, "tshe","iso","8303165495086","0795262301"))));
        }});

        ResponseEntity<Customer> customerResponseEntity = (ResponseEntity<Customer>) customerResource.get(1L);
        Assert.assertNotNull(customerResponseEntity);
        Assert.assertEquals(customerResponseEntity.getBody().getId(), Long.valueOf(1));

        mockery.assertIsSatisfied();

    }



    @Test
    public void saveCustomerTest() throws URISyntaxException {

        Customer customer = createCustomer(1L, "tshe","iso","8303165495086","0795262301");

        mockery.checking( new Expectations(){{
            atLeast(1).of(customerService).save(customer);
            will(returnValue( customer));
        }});

        mockery.checking( new Expectations(){{
            atLeast(1).of(customerService).get(1L);
            will(returnValue( Optional.ofNullable(null)));
        }});

        ResponseEntity<Customer> customerResponseEntity = (ResponseEntity<Customer>) customerResource.save(customer, UriComponentsBuilder.fromUri(new URI("/")));
        Assert.assertNotNull(customerResponseEntity);
        Assert.assertEquals(customerResponseEntity.getHeaders().get("location").get(0), "/customer/1");

        mockery.assertIsSatisfied();

    }


    @Test
    public void updateCustomerTest() {

        Customer customer = createCustomer(1L, "tshe","iso","8303165495086","0795262301");

        mockery.checking( new Expectations(){{
            atLeast(1).of(customerService).update(customer);

            customer.setLastName("changeme");
            will(returnValue( customer));
        }});


        ResponseEntity<Customer> customerResponseEntity = (ResponseEntity<Customer>) customerResource.update(customer);
        Assert.assertNotNull(customerResponseEntity);
        Assert.assertEquals(customerResponseEntity.getBody(), customer);

        mockery.assertIsSatisfied();

    }



    @Test
    public void deleteCustomerTest() {


        mockery.checking( new Expectations(){{
            atLeast(1).of(customerService).delete(1L);

        }});


        ResponseEntity<?> deleteResonse = customerResource.delete(1L);
        Assert.assertEquals(deleteResonse.getStatusCode().value(), 200);

        mockery.assertIsSatisfied();

    }


    private Customer createCustomer(Long id, String firstName, String lastName, String idNumber, String mobile){

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setIdNumber(idNumber);
        customer.setMobileNumber(mobile);

        return customer;
    }


}