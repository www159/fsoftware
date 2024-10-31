package com.example.nationaltax.controller;

import com.example.nationaltax.bean.SentRecord;
import com.example.nationaltax.service.SentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sentRecord")
public class SentRecordController {
    @Autowired
    SentRecordService sentRecordService;


    @GetMapping("/{id}")
    public SentRecord getSentRecord(@PathVariable Long id) {
        return sentRecordService.getById(id);
    }

    @PostMapping("/")
    public boolean createSentRecord(@RequestBody SentRecord sentRecord) {
        return sentRecordService.save(sentRecord);
    }

    @PutMapping("/{id}")
    public boolean updateSentRecord(@PathVariable Long id, @RequestBody SentRecord sentRecord) {
        sentRecord.setTaskId(id);
        return sentRecordService.updateById(sentRecord);
    }

    @DeleteMapping("/{id}")
    public boolean deleteSentRecord(@PathVariable Long id) {
        return sentRecordService.removeById(id);
    }

    @GetMapping("search/records")
    public List<SentRecord> searchSentRocords(
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Boolean isSent,
            @RequestParam(required = false) String sendMethod
    ) {
        return sentRecordService.searchNotificationEntries(phoneNumber, isSent, sendMethod);
    }
}
