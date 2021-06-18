package za.co.investec.cis.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.service.CustomerService;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerManagementApplicationTests {

	/**
	 * Test that applicationContext is able to load
	 *  - scan + load components
	 *  - components are able to load properly
	 */

	@Autowired
	CustomerService customerService;


	@Test
	@Order(0)
	public void contextLoadsTest() {
	}


	@Test
	@Order(1)
	public void customerCreateTest() {
		Customer customer = customerService.save(getCustomer());
		Assert.assertNotNull(customer);
	}

	@Test
	@Order(2)
	public void customerServiceGETTest() {
		Optional<Customer> customerOptional = customerService.get(1L);
		Assert.assertNotNull(customerOptional.get());
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
