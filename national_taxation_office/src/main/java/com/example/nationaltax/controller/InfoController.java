package com.example.nationaltax.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.Info;
import com.example.nationaltax.service.InfoService;


@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    @PostMapping("")
    public ResponseEntity<Info> addInfo(@RequestBody Info info) {
        infoService.save(info);
        return ResponseEntity.ok(info);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Info> getInfoById(@PathVariable long id) {
        return ResponseEntity.ok(infoService.getById(id));
    }

    @GetMapping("")
    @Profile("dev")
    public ResponseEntity<Info> getInfoByTitle(@RequestParam String title) {
        QueryWrapper<Info> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        return ResponseEntity.ok(infoService.getOne(queryWrapper));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInfo(@PathVariable long id, @RequestBody Info info) {
        info.setInfoId(id);
        return Util.stringRes(infoService.updateById(info), "公告或者id错误");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInfo(@PathVariable long id) {
        return Util.stringRes(infoService.removeById(id), "公告id错误");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Info>> listInfo(@RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String title,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") String time) {
        QueryWrapper<Info> queryWrapper = new QueryWrapper<>();
        if (title != null) {
            queryWrapper.like("title", title);
        } 
        if(time != null) {
            queryWrapper.like("time", time);
        }
        queryWrapper.last("order by time desc");
        return ResponseEntity.ok(infoService.page(new Page<>(page, size, false), queryWrapper).getRecords());
    }
}
