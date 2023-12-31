package com.example.pos_spring_boot.service;

import com.example.pos_spring_boot.dto.CustomerDTO;
import com.example.pos_spring_boot.dto.ItemDTO;
import com.example.pos_spring_boot.dto.OrderDTO;
import com.example.pos_spring_boot.entity.Customer;
import com.example.pos_spring_boot.entity.Item;
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
        OrderDTO orderDTO = modelMapper.map(order_t.get(), OrderDTO.class);

        List<ItemDTO> items = new ArrayList<>();
        for (OrderItem orderItem : order_t.get().getOrderItems()) {
            Item item = orderItem.getItem();
            items.add(
                    new ItemDTO(
                            item.getICode() ,
                            item.getIName() ,
                            item.getIPrice() ,
                            orderItem.getQty()
                    )
            );
        }

        orderDTO.setItems(items);
        orderDTO.setCustomerDTO(modelMapper.map(order_t.get().getCustomer() , CustomerDTO.class));

        return orderDTO;

    }

    public boolean placeOrder(OrderDTO orderDTO) throws Exception {
        if (orderRepo.existsById(orderDTO.getoId())){
            throw new Exception();
        }

        for (ItemDTO itemDTO : orderDTO.getItems()) {
            ItemDTO tempItem = itemService.searchItem(itemDTO.getiCode());

            tempItem.setiQty(tempItem.getiQty() - itemDTO.getiQty());

            if (tempItem.getiQty() < 0) {
                throw new Exception();
            }

            itemService.updateItem(tempItem);

        }
        Order_t order_t = modelMapper.map(orderDTO, Order_t.class);

        List<OrderItem> orderItems = new ArrayList<>();
        for (ItemDTO itemDTO : orderDTO.getItems()) {
            orderItems.add(
                    new OrderItem(order_t , modelMapper.map(itemDTO , Item.class ) , itemDTO.getiQty())
            );
        }

        order_t.setOrderItems(orderItems);

        orderRepo.save(order_t);
        return true;
    }


}
