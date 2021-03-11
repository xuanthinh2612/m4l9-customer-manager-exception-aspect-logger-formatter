package practice.repository;


import practice.model.Customer;
import practice.model.Province;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends PagingAndSortingRepository<Customer, Long> {

    //find by province
    List<Customer> findAllByProvince(Province province);

    //find by name
    @Query (value = "select * from Customer where Customer.name like ?",nativeQuery = true)
    List<Customer> findCustomerByName(String name);

}
