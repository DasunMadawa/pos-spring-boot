package com.example.pos_spring_boot.controller;

import com.example.pos_spring_boot.dto.CustomerDTO;
import com.example.pos_spring_boot.dto.ResponseDTO;
import com.example.pos_spring_boot.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pos_system/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ResponseDTO responseDTO;

    @GetMapping
    public ResponseEntity getCustomer(@RequestParam String cId) {
        try {
            CustomerDTO customerDTO = customerService.searchCustomer(cId);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerDTO);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Can't find customer");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity getAllCustomers() {
        try {
            List<CustomerDTO> customerDTOList = customerService.getAllCustomers();

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(customerDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Error !");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping
    public ResponseEntity putCustomer(@RequestBody CustomerDTO customerDTO) {
        try {
            boolean isUpdated = customerService.updateCustomer(customerDTO);

            if (isUpdated) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Can't find customer");
                responseDTO.setContent(customerDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(customerDTO);

            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping
    public ResponseEntity postCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println(customerDTO);

        try {
            boolean isSaved = customerService.saveCustomer(customerDTO);

            if (isSaved) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(customerDTO);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Check duplicate customer IDs");
                responseDTO.setContent(customerDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(customerDTO);

            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping
    public ResponseEntity deleteCustomer(@RequestParam String cId) {

        try {

            boolean isDeleted = customerService.deleteCustomer(cId);

            if (isDeleted) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Check customer Id");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error !");
            responseDTO.setContent(null);

            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}
