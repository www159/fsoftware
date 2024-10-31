package com.example.nationaltax.controller;

import java.util.Date;
import java.util.List;

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
import com.example.nationaltax.bean.Complaint;
import com.example.nationaltax.bean.ComplaintReply;
import com.example.nationaltax.service.ComplaintReplyService;
import com.example.nationaltax.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private ComplaintReplyService complaintReplyService;

    @PostMapping("")
    public ResponseEntity<String> addComplaint(@RequestBody Complaint complaint) {
        return Util.stringRes(complaintService.save(complaint), "投诉错误");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateComplaint(@PathVariable long id, @RequestBody Complaint complaint) {
        complaint.setComplaintId(id);
        return Util.stringRes(complaintService.updateById(complaint), "投诉或者id错误");
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteComplaint(@RequestBody List<Long> ids) {
        return Util.stringRes(complaintService.removeByIds(ids), "投诉id错误");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaint(@PathVariable long id) {
        return ResponseEntity.ok(complaintService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Complaint>> listComplaint(@RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page, @RequestParam(required = false) String toComplaintDept,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @RequestParam(required = false) Integer state) {
        QueryWrapper<Complaint> queryWrapper = new QueryWrapper<>();
        if (toComplaintDept != null) {
            queryWrapper.like("to_complaint_dept", toComplaintDept);
        }
        if (startDate != null && endDate != null) {
            queryWrapper.between("time", startDate, endDate);
        }
        if (state != null) {
            queryWrapper.eq("state", state);
        }
        return ResponseEntity.ok(complaintService.page(new Page<>(page, size, false), queryWrapper).getRecords());
    }

    @PostMapping("/reply")
    public ResponseEntity<String> replayComplaint(@RequestBody ComplaintReply complaintReply) {
        // 一旦回复投诉，则投诉受理
        Complaint complaint = complaintService.getById(complaintReply.getComplaintId());

        if (complaint.getState() == 0) {
            complaint.setState(1);
            if (!complaintService.updateById(complaint)) {
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("投诉无法受理");
            }
        }

        return Util.stringRes(complaintReplyService.save(complaintReply), "投诉回复错误");
    }

    @DeleteMapping("/reply/{id}")
    public ResponseEntity<String> deleteComplaintReply(@PathVariable long id) {
        return Util.stringRes(complaintReplyService.removeById(id), "投诉回复id错误");
    }

    @GetMapping("/reply/list")
    public ResponseEntity<List<ComplaintReply>> listComplaintReply(@RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page, @RequestParam long id) {
        QueryWrapper<ComplaintReply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("complaint_id", id);

        return ResponseEntity
                .ok(complaintReplyService.page(new Page<ComplaintReply>(page, size, false), queryWrapper).getRecords());
    }

}