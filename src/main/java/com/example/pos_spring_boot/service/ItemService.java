package com.example.pos_spring_boot.service;

import com.example.pos_spring_boot.dto.ItemDTO;
import com.example.pos_spring_boot.entity.Item;
import com.example.pos_spring_boot.repo.ItemRepo;
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
public class ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ItemDTO searchItem(String id) {
        Optional<Item> item = itemRepo.findById(id);
        return modelMapper.map(item.get() , ItemDTO.class);
    }

    public List<ItemDTO> getAllItems() {
        return modelMapper.map(itemRepo.findAll() , new TypeToken<ArrayList<ItemDTO>>(){}.getType());
    }

    public boolean saveItem(ItemDTO itemDTO) {
        if (!itemRepo.existsById(itemDTO.getiCode())) {
            itemRepo.save(modelMapper.map(itemDTO , Item.class));
            return true;

        } else {
            return false;
        }
    }

    public boolean updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getiCode())) {
            itemRepo.save(modelMapper.map(itemDTO , Item.class));
            return true;

        } else {
            return false;
        }
    }

    public boolean deleteItem(String id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return true;

        } else {
            return false;
        }

    }
}
