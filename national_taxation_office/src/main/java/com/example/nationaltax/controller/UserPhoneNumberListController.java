package com.example.nationaltax.controller;

import com.example.nationaltax.bean.UserPhoneNumberList;
import com.example.nationaltax.service.UserPhoneNumberListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userPhoneNumberLists")
public class UserPhoneNumberListController {
    @Autowired
    UserPhoneNumberListService userPhoneNumberListService;

    @PostMapping
    public ResponseEntity<String> addUserPhoneNumberList(@RequestBody UserPhoneNumberList userPhoneNumberList) {
        userPhoneNumberListService.addUserPhoneNumberList(userPhoneNumberList);
        return ResponseEntity.ok("User phone number list added successfully.");
    }

    @GetMapping
    public List<UserPhoneNumberList> getAllUserPhoneNumberLists() {
        return userPhoneNumberListService.getAllUserPhoneNumberLists();
    }

}
