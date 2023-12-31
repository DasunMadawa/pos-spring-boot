package com.example.pos_spring_boot.controller;

import com.example.pos_spring_boot.dto.OrderDTO;
import com.example.pos_spring_boot.dto.ResponseDTO;
import com.example.pos_spring_boot.service.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos_system/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ResponseDTO responseDTO;

    @GetMapping
    public ResponseEntity getOrder(@RequestParam String oId) {
        try {
            OrderDTO orderDTO = orderService.getOrder(oId);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(orderDTO);

            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Can't find order , check another oId");
            responseDTO.setContent(null);

            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping
    public ResponseEntity PostOrder(@RequestBody OrderDTO orderDTO) {
        try {
            boolean isPlaced = orderService.placeOrder(orderDTO);

            if (isPlaced) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(orderDTO);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            }


        } catch (Exception e) {
            e.printStackTrace();

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Check item qty s , Or duplicate oId s");
            responseDTO.setContent(null);

            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }
    }

}
