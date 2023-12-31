package com.example.pos_spring_boot.service;

import com.example.pos_spring_boot.dto.ItemDTO;
import com.example.pos_spring_boot.dto.OrderDTO;
import com.example.pos_spring_boot.dto.OrderItemDTO;
import com.example.pos_spring_boot.entity.OrderItem;
import com.example.pos_spring_boot.entity.Order_t;
import com.example.pos_spring_boot.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class
OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelMapper modelMapper;

    public OrderDTO getOrder(String id) {
        Optional<Order_t> order_t = orderRepo.findById(id);
        return modelMapper.map(order_t.get() , OrderDTO.class);

    }

    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
        if (orderRepo.existsById(orderDTO.getoId())){
            throw new Exception();
        }

        for (OrderItemDTO orderItemDTO : orderDTO.getOrderItemDTOList()) {
            ItemDTO itemDTO = orderItemDTO.getItemDTO();
            ItemDTO tempItem = itemService.searchItem(itemDTO.getiCode());

            tempItem.setiQty(tempItem.getiQty() - orderItemDTO.getQty());

            if (tempItem.getiQty() < 0) {
                throw new Exception();
            }

            itemService.updateItem(tempItem);

        }
        Order_t order_t = modelMapper.map(orderDTO, Order_t.class);
        order_t.setOrderItems(modelMapper.map(orderDTO.getOrderItemDTOList() , new TypeToken<ArrayList<OrderItem>>() {}.getType()));
        System.out.println(order_t.getOrderItems());
//        int i = 0;
//        for (OrderItem orderItem : orderItems) {
//            orderItem.setOrder_t(order_t);
//            System.out.println(i++);
//        }

        orderRepo.save(order_t);
        return true;
    }


}
