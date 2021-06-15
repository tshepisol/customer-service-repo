package za.co.investec.cis.service;

import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.domain.CustomerSearchDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> findAll();

    List<Customer> search(CustomerSearchDTO customerSearchDTO);

    Optional<Customer> get(Long id);

    Customer save(Customer customer);

    Customer update(Customer customer);

    void delete(Long id);

}
