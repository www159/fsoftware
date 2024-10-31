package com.example.nationaltax.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.tokenizer.Result;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.EasyNotify;
import com.example.nationaltax.bean.NotifyMail;
import com.example.nationaltax.mapper.NotifyMailMapper;
import com.example.nationaltax.bean.User;
import com.example.nationaltax.service.EasyNotifyService;
import com.example.nationaltax.service.EmailService;
import com.example.nationaltax.service.NotifyMailService;
import com.example.nationaltax.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/easyNotify")
public class EasyNotifyController {

    @Autowired
    EasyNotifyService easyNotifyService;
    @Autowired
    EmailService emailService;
    @Autowired
    NotifyMailService notifyMailService;

    @GetMapping
    public List<EasyNotify> getAllEasyNotifyRecords() {
        return easyNotifyService.getAllEasyNotifys();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createEasyNotify(@RequestBody EasyNotify easyNotify) {
        easyNotifyService.saveEasyNotify(easyNotify);
        return ResponseEntity.ok("EasyNotify added successfully.");
    }

    @GetMapping("/search")
    public ResponseEntity<List<EasyNotify>> searchEasyNotifyList(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(required = false) String title,
                                                                 @RequestParam(required = false) String type,
                                                                 @RequestParam(required = false) String status) {
        QueryWrapper<EasyNotify> queryWrapper = new QueryWrapper<>();

        if (title != null) {
            queryWrapper.like("title", title);
        }
        if (type != null) {
            queryWrapper.like("type", type);
        }
        if (status != null ) {
            queryWrapper.like("status", status);
        }
        return ResponseEntity
                .ok(easyNotifyService.page(new Page<EasyNotify>(page, size, false), queryWrapper).getRecords());
    }

    @PostMapping("/savePhoneNumber")
    public ResponseEntity<String> addPhoneNumber(@RequestParam Long notifyId,@RequestParam String phoneNumber) {
        easyNotifyService.savePhoneNumber(notifyId,phoneNumber);
        String message = "电话保存成功";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        boolean success = easyNotifyService.updateStatusById(id, status);
        if (success) {
            return "Status updated successfully.";
        } else {
            return "Failed to update status.";
        }
    }

    @PostMapping("/sendEmail/cuiban")
    public String Reminder1 (Long notifyId) {
        try {
            List<String> emails = notifyMailService.getMailByNotifyId(notifyId);
            for (String email : emails) {
                emailService.sendMail(
                        "3341322301@qq.com",
                        email,
                        "3341322301@qq.com",
                        "催办提醒",
                        "欢迎使用国税OA系统，您有一个催办业务，请注意处理。");
            }
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }

    @PostMapping("/sendEmail/cuijiao")
    public String Reminder2 (Long notifyId){
        try {
            List<String> emails = notifyMailService.getMailByNotifyId(notifyId);
            for (String email : emails) {
            emailService.sendMail(
                    "3341322301@qq.com",
                    email,
                    "3341322301@qq.com",
                    "催缴提醒",
                    "欢迎使用国税OA系统，您有一个催缴业务，请注意处理。");
            }
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }
    }

    @PostMapping("/notifymail/batchInsert")
    public ResponseEntity<String> batchInsert(@RequestParam("notifyId") Long notifyId, @RequestParam("mails") List<String> mails) {
        notifyMailService.batchInsert(notifyId, mails);

        return ResponseEntity.status(HttpStatus.CREATED).body("批量插入成功");
    }

    @GetMapping("/getEmails/{notifyId}")
    public ResponseEntity<List<NotifyMail>> getEmailsByNotifyId(@PathVariable Long notifyId) {
        List<NotifyMail> notifyMails = notifyMailService.getByNotifyId(notifyId);
        return ResponseEntity.ok(notifyMails);
    }


    //上传excel批量导入
    @PostMapping("/import")
    public void imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
        List<List<Object>> list = reader.read(1); // 从第二行开始读
        // 创建一个List集合
        List<NotifyMail> NotifyMails = CollUtil.newArrayList();
        // 遍历
        for (List<Object> row : list) {
            NotifyMail NotifyMail = new NotifyMail();
            // 转换成字符串
            NotifyMail.setNotifyId(Long.valueOf(row.get(0).toString()));
            NotifyMail.setMail(row.get(1).toString());
            // 遍历完一个添加一个
            NotifyMails.add(NotifyMail);
        }
        // 调用mapper添加到数据库
        notifyMailService.saveBatch(NotifyMails);
        return ;
    }

}
