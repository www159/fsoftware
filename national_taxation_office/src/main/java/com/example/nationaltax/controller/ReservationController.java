package com.example.nationaltax.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.Reservation;
import com.example.nationaltax.bean.ReservationVo;
import com.example.nationaltax.mapper.ReservationMapper;
import com.example.nationaltax.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationMapper reservationMapper;

    @PutMapping("/update/{id}")
    public String updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.setReservationId(id);
        reservationService.updateById(reservation);
        return "Reservation updated successfully";
    }

    @PostMapping("/addReservation")
    public String addReservation(@RequestBody Reservation reservation) {
        reservationService.save(reservation);
        return "Reservation added successfully";
    }

    @GetMapping("/getAll")
    public List<Reservation> getAllReservations() {
        return reservationService.list();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.removeById(id);
        return "Reservation deleted successfully";
    }

    @GetMapping("/reservation/byitemname/{itemName}")
    public List<Reservation> getReservationByItemName(@PathVariable("itemName") String itemName)
            throws UnsupportedEncodingException {
        String decodedItemName = java.net.URLDecoder.decode(itemName, "UTF-8");
        return reservationService.getReservationByItemName(decodedItemName);
    }

    @GetMapping("/reservation/idsbyname/{name}")
    public List<Long> getReservationItemIdsByName(@PathVariable String name) {
        return reservationService.getReservationItemIdsByName(name);
    }

    @GetMapping("/{reservationItemId}")
    public ResponseEntity<List<Reservation>> getReservations(@PathVariable long reservationItemId) {
        List<Reservation> reservations = reservationService.findByReservationItemId(reservationItemId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ReservationVo>> searchReservationList(@RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String reservationItemName,
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        QueryWrapper<ReservationVo> queryWrapper = new QueryWrapper<>();

        if (reservationItemName != null) {
            queryWrapper.like("ri.name", reservationItemName);
        }
        if (state != null) {
            queryWrapper.eq("r.state", state);
        }

        if (startDate != null && endDate != null) {
            queryWrapper.between("reservation_time", startDate, endDate);
        }

        Page<ReservationVo> reservationPage = new Page<>(page, size, false);
        Page<ReservationVo> resultPage = reservationMapper.selectReservationList(reservationPage, queryWrapper);
        List<ReservationVo> resultList = resultPage.getRecords();
        return ResponseEntity.ok(resultList);
    }
}
