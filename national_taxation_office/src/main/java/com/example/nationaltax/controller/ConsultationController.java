package com.example.nationaltax.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.nationaltax.bean.Consultation;
import com.example.nationaltax.bean.ConsultationReply;
import com.example.nationaltax.service.ConsultationReplyService;
import com.example.nationaltax.service.ConsultationService;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private ConsultationReplyService consultationReplyService;
    
    private Logger logger = LoggerFactory.getLogger(ConsultationController.class);

    @PostMapping("")
    public ResponseEntity<String> addConsultation(@RequestBody Consultation consultation) {
        return Util.stringRes(consultationService.save(consultation), "咨询错误");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationVO(@PathVariable long id) {
        return ResponseEntity.ok(consultationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateConsultation(@PathVariable long id, @RequestBody Consultation consultation) {
        consultation.setConsultationId(id);
        return Util.stringRes(consultationService.updateById(consultation), "咨询或者id错误");
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteConsultations(@RequestBody List<Long> ids) {
        return Util.stringRes(consultationService.removeByIds(ids), "id错误");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Consultation>>

            listConsultation(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page,
                    @RequestParam(required = false) String title,
                    @RequestParam(required = false) Integer state,
                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        QueryWrapper<Consultation> queryWrapper = new QueryWrapper<>();

        if (title != null) {
            queryWrapper.like("title", title);
        }
        if (state != null) {
            queryWrapper.eq("state", state);
        }
        if (startDate != null && endDate != null) {
            logger.debug("times are: {}, {}", startDate, endDate);
            queryWrapper.between("time", startDate, endDate);
        }

        return ResponseEntity
                .ok(consultationService.page(new Page<Consultation>(page, size, false), queryWrapper).getRecords());
    }

    @PostMapping("/reply")
    public ResponseEntity<String> replayConsultation(@RequestBody ConsultationReply consultationReply) {
        Consultation consultation = consultationService.getById(consultationReply.getConsultationId());

        if (consultation.getState() == 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("咨询已回复");
        }

        consultation.setState(1);
        if (!consultationService.updateById(consultation)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("咨询无法处理");
        }

        return Util.stringRes(consultationReplyService.save(consultationReply), "咨询回复或id错误");
    }

    @GetMapping("/reply/{id}")
    public ResponseEntity<ConsultationReply> getConsultationReply(@PathVariable long id) {
        return ResponseEntity.ok(consultationReplyService.getByConsultationId(id));
    }
}
