package com.example.nationaltax.controller;

import com.example.nationaltax.bean.ReservationHandler;
import com.example.nationaltax.service.ReservationHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ReservationHandler")
public class ReservationHandlerController {
    @Autowired
    ReservationHandlerService reservationHandlerService;


    @PutMapping("/update/{id}")
    public String updateReservationHandler(@PathVariable Long id, @RequestBody ReservationHandler reservationHandler) {
        reservationHandler.setReservationHandlerId(id);
        reservationHandlerService.updateById(reservationHandler);
        return "ReservationHandler updated successfully";
    }

    @PostMapping("/addReservationHandler")
    public String addReservationHandler(@RequestBody ReservationHandler reservationHandler) {
        reservationHandlerService.save(reservationHandler);
        return "ReservationHandler added successfully";
    }

    @GetMapping("/getAll")
    public List<ReservationHandler> getAllReservationHandlers() {
        return reservationHandlerService.list();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservationHandler(@PathVariable Long id) {
        reservationHandlerService.removeById(id);
        return "ReservationHandler deleted successfully";
    }

}
