package com.example.nationaltax.controller;

import com.example.nationaltax.bean.NotificationTask;
import com.example.nationaltax.service.NotificationTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificationTask")
public class NotificationTaskController {
    @Autowired
    NotificationTaskService notificationTaskService;


    @PostMapping("/create")
    public void createNotificationTask(@RequestBody NotificationTask notificationTask) {
        notificationTaskService.save(notificationTask);
    }

    @GetMapping("/{id}")
    public NotificationTask getNotificationTaskById(@PathVariable("id") Long id) {
        return notificationTaskService.getById(id);
    }

    @PutMapping("/{id}")
    public void updateNotificationTask(@PathVariable("id") Long id, @RequestBody NotificationTask notificationTask) {
        notificationTask.setId(id);
        notificationTaskService.updateById(notificationTask);
    }

    @DeleteMapping("/{id}")
    public void deleteNotificationTask(@PathVariable("id") Long id) {
        notificationTaskService.removeById(id);
    }


}
