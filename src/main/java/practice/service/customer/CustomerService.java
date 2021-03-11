package practice.service.customer;

import practice.exception.PageNotFoundException;
import practice.model.Customer;
import practice.model.Province;
import practice.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import practice.repository.ICustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IProvinceService provinceService;

    @Override
    public List<Customer> findALl() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Page<Customer> findALl(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findById(Long id) throws PageNotFoundException {
        Customer customer = customerRepository.findOne(id);
        if (customer==null){
            throw  new PageNotFoundException();
        } else return customer;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.delete(id);
    }


    @Override
    public List<Customer> findByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    public List<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }
}
