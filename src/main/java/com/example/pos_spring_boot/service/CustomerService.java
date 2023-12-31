package com.example.pos_spring_boot.service;

import com.example.pos_spring_boot.dto.CustomerDTO;
import com.example.pos_spring_boot.entity.Customer;
import com.example.pos_spring_boot.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO searchCustomer(String id) {
        Optional<Customer> customerList = customerRepo.findById(id);
        return modelMapper.map(customerList.get(), CustomerDTO.class);

    }

    public boolean saveCustomer(CustomerDTO customerDTO) {
        if (!customerRepo.existsById(customerDTO.getcId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return true;

        } else {
            return false;
        }

    }

    public boolean updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getcId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
            return true;

        } else {
            return false;
        }

    }

    public boolean deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
            return true;

        } else {
            return false;
        }

    }

    public List<CustomerDTO> getAllCustomers() {
        return modelMapper.map(customerRepo.findAll() , new TypeToken<ArrayList<CustomerDTO>>(){}.getType());

    }


}
