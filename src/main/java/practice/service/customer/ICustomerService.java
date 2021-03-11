package practice.service.customer;

import practice.model.Customer;
import practice.model.Province;
import practice.service.IService;

import java.util.List;

public interface ICustomerService extends IService<Customer> {
 List<Customer> findAllByProvince(Province province);
}
