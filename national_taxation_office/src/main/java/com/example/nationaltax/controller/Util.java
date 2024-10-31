package com.example.nationaltax.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    static ResponseEntity<String> stringRes(boolean cond, String errorMsg) {
        if(cond) {
            return ResponseEntity.ok("成功");
        }
        else return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("公告或id错误");
    }
}
