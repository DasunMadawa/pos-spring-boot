package com.example.pos_spring_boot.controller;

import com.example.pos_spring_boot.dto.ItemDTO;
import com.example.pos_spring_boot.dto.ResponseDTO;
import com.example.pos_spring_boot.service.ItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pos_system/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    ResponseDTO responseDTO;

    @GetMapping
    public ResponseEntity getItem(@RequestParam String iCode) {
        try {
            ItemDTO itemDTO = itemService.searchItem(iCode);

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(itemDTO);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Can't find item");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping(value = "/getAllItems")
    public ResponseEntity getAllItems() {
        try {
            List<ItemDTO> itemDTOList = itemService.getAllItems();

            responseDTO.setCode(HttpServletResponse.SC_OK);
            responseDTO.setMessage("Success");
            responseDTO.setContent(itemDTOList);
            return new ResponseEntity(responseDTO, HttpStatus.OK);

        } catch (Exception e) {

            responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
            responseDTO.setMessage("Error !");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping
    public ResponseEntity putItem(@RequestBody ItemDTO itemDTO) {
        try {
            boolean isUpdated = itemService.updateItem(itemDTO);

            if (isUpdated) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(itemDTO);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Can't find Item");
                responseDTO.setContent(itemDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(itemDTO);

            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PostMapping
    public ResponseEntity postItem(@RequestBody ItemDTO itemDTO) {
        try {
            boolean isSaved = itemService.saveItem(itemDTO);

            if (isSaved) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(itemDTO);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Check duplicate item codes");
                responseDTO.setContent(itemDTO);

                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseDTO.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseDTO.setMessage("Error");
            responseDTO.setContent(itemDTO);

            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping
    public ResponseEntity deleteItem(@RequestParam String iCode) {

        try {

            boolean isDeleted = itemService.deleteItem(iCode);

            if (isDeleted) {
                responseDTO.setCode(HttpServletResponse.SC_OK);
                responseDTO.setMessage("Success");
                responseDTO.setContent(null);

                return new ResponseEntity(responseDTO, HttpStatus.OK);
            } else {

                responseDTO.setCode(HttpServletResponse.SC_BAD_REQUEST);
                responseDTO.setMessage("Check item code");
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
