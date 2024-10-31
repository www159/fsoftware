package com.example.nationaltax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.ReservationItem;
import com.example.nationaltax.service.ReservationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ReservationItem")
public class ReservationItemController {
     @Autowired
    ReservationItemService reservationItemService;

    @PutMapping("/update/{id}")
    public String updateReservationItem(@PathVariable Long id, @RequestBody ReservationItem reservationItem) {
        reservationItem.setReservationItemId(id);
        reservationItemService.updateById(reservationItem);
        return "ReservationItem updated successfully";
    }

    @PostMapping("/addReservationItem")
    public String addReservationItem(@RequestBody ReservationItem reservationItem) {
        reservationItemService.save(reservationItem);
        return "ReservationItem added successfully";
    }

    @GetMapping("/getAll")
    public List<ReservationItem> getAllReservationItems() {
        return reservationItemService.list();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservationItem(@PathVariable Long id) {
        reservationItemService.removeById(id);
        return "ReservationItem deleted successfully";
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReservationItem>> searchReservationItemList(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String department) {
        QueryWrapper<ReservationItem> queryWrapper = new QueryWrapper<>();

        if (name != null) {
            queryWrapper.like("name", name);
        }
        if (department != null ) {
            queryWrapper.like("department", department);
        }
        return ResponseEntity
                .ok(reservationItemService.page(new Page<ReservationItem>(page, size, false), queryWrapper).getRecords());

    }

    @GetMapping("/searchByName")
    public List<Long> searchReservationItemListByName(@RequestParam String name){
        return reservationItemService.getReservationItemIdsByName(name);
    }


}
