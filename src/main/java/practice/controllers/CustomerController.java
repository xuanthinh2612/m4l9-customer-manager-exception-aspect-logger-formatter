package practice.controllers;


import practice.exception.PageNotFoundException;
import practice.model.Customer;
import practice.model.Province;
import practice.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import practice.service.customer.ICustomerService;

import java.util.List;

@Controller
@RequestMapping("/home")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;

    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView showErrors() {
        return new ModelAndView("pageNotFound");
    }

    @ModelAttribute("provinceList")
    public List<Province> provinceList() {
        return provinceService.findALl();
    }


    @GetMapping("/show")
    public ModelAndView showAll(@PageableDefault(size = 5) Pageable pageable) {

        ModelAndView modelAndView = new ModelAndView("list");
        Page<Customer> customerList = customerService.findALl(pageable);
        modelAndView.addObject("customerList", customerList);
        return modelAndView;

    }

    @GetMapping("/create")
    public ModelAndView showCreate() {

        ModelAndView modelAndView = new ModelAndView("create");

        modelAndView.addObject("customer", new Customer());

        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/home/show";
    }

    @GetMapping("/update/{id}")

    public ModelAndView showUpdate(@PathVariable long id) throws PageNotFoundException {

        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", customerService.findById(id));

        return modelAndView;

    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute Customer customer) {
        customer.setId(id);
        customerService.save(customer);
        return "redirect:/home/show";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable long id) throws PageNotFoundException {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        customerService.deleteById(id);
        return "redirect:/home/show";
    }

    @PostMapping("/search")
    public ModelAndView resultSearch(@RequestParam String keyWord) {
        ModelAndView modelAndView = new ModelAndView("list");
        String name = "%" + keyWord + "%";
        List<Customer> customerList = customerService.findByName(name);
        modelAndView.addObject("customerList", customerList);
        return modelAndView;
    }

    @GetMapping("/showByProvince/{id}")
    public ModelAndView showByProvince1(@PathVariable long id) throws PageNotFoundException {
        Province province = provinceService.findById(id);
        List<Customer> customerList = customerService.findAllByProvince(province);
        ModelAndView modelAndView = new ModelAndView("listByProvince");
        modelAndView.addObject("customerList", customerList);

        return modelAndView;
    }

    @GetMapping("/showByProvince")
    public ModelAndView showByProvince2(@RequestParam long province_id) throws PageNotFoundException {

        Province province = provinceService.findById(province_id);
        List<Customer> customerList = customerService.findAllByProvince(province);
        ModelAndView modelAndView = new ModelAndView("listByProvince");
        modelAndView.addObject("customerList", customerList);

        return modelAndView;
    }
}
