package za.co.investec.cis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.investec.cis.domain.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    List<Customer> findByFirstNameLikeOrIdNumberLikeOrMobileNumberLike(String firstName, String idNumber, String mobileNumber);


}
