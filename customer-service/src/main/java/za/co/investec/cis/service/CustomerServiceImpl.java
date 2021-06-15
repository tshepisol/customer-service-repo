package za.co.investec.cis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.investec.cis.dao.CustomerRepository;
import za.co.investec.cis.domain.Customer;
import za.co.investec.cis.domain.CustomerSearchDTO;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> search(CustomerSearchDTO customerSearchDTO) {
        return customerRepository.findByFirstNameLikeOrIdNumberLikeOrMobileNumberLike(customerSearchDTO.getFirstName(), customerSearchDTO.getIdNumber(), customerSearchDTO.getMobileNumber());
    }

    @Override
    public Optional<Customer> get(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer save(Customer customer) {
       return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
       return save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
