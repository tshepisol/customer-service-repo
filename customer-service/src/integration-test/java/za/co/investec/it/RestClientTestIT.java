package za.co.investec.it;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import za.co.investec.cis.domain.Citizen;
import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.domain.Gender;

import java.net.URI;

/**
 *
 *   once the application deployed
 *  - integration test - application can be invoked by client application
 *
 */
public class RestClientTestIT {

    private  static final String BASE_URL = "http://localhost:8080/CustomerManagementService/customer";


    @Test
    public void createCustomerTest(){

        Customer customer = getCustomer();
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(BASE_URL, customer, Customer.class);

        Assert.assertNotNull(uri.toASCIIString());

        System.out.println("URL to retrieve Customer:"+uri.toASCIIString());

    }


    /**
     * Validates Gender + Citizenship position of the ID number
     * Gender + Citizen are not part of the requirement. validation will be optional
     */
    @Test
    public void IdValidationTest(){

        Customer customer = getCustomer();
        customer.setIdNumber("9101015800086");
        customer.setMobileNumber("0827546603");
        customer.setGender(Gender.MALE);
        customer.setCitizenship(Citizen.SA);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> customerResponseEntity = restTemplate.postForEntity(BASE_URL, customer, Customer.class);

        Assert.assertNotNull(customerResponseEntity);

        System.out.println("URL to retrieve Customer:"+customerResponseEntity);

    }



    @Test
    public void retrieveTest(){



        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Customer> customerResponseEntity = restTemplate.getForEntity(BASE_URL+"/1", Customer.class);

        Assert.assertNotNull(customerResponseEntity);

        System.out.println("URL to retrieve Customer:"+customerResponseEntity);

    }


    @Test
    public void deleteTest(){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(BASE_URL+"/1");

    }


    @Test(expected = HttpClientErrorException.class)
    public void beanValidationTest(){

        Customer customer = getCustomer();
        customer.setFirstName(null);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(BASE_URL, customer, Customer.class);
    }


    private Customer getCustomer(){
        Customer customer = new Customer();
        customer.setFirstName("bat");
        customer.setLastName("man");
        customer.setMobileNumber("0795262301");
        customer.setIdNumber("8303165495086");

        return customer;
    }
}
