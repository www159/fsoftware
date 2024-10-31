package com.example.nationaltax.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.nationaltax.bean.Complaint;
import com.example.nationaltax.util.MockObject;
@SpringBootTest
public class ComplaintServiceTest {
    @Autowired
    private ComplaintService complaintService;

    private static Logger logger = LoggerFactory.getLogger(ComplaintServiceTest.class);


    @Test
    public void saveTest() {
        Complaint complaint = MockObject.mockComplaint("some");

        boolean result = complaintService.save(complaint);

        logger.info("id is {}", complaint.getComplaintId());

        assertTrue(result);
    }

    @Test
    public void getByIdTest() {
        Complaint complaint = MockObject.mockComplaint("get by id");

        complaintService.save(complaint);

        Complaint result = complaintService.getById(complaint.getComplaintId());

        assertTrue(result.getContent().equals(new String("get by id content")));
    }

    @Test
    public void updateByIdTest() {
        Complaint complaint = MockObject.mockComplaint("update by id");

        complaintService.save(complaint);
        complaint.setContent("updated");

        boolean result = complaintService.updateById(complaint);

        assertTrue(result);
        complaint = complaintService.getById(complaint.getComplaintId());
        assertTrue(complaint.getContent().equals(new String("updated")));
    }

    @Test
    public void removeByIdTest() {
        Complaint complaint = MockObject.mockComplaint("remove by id");
        
        boolean result = complaintService.save(complaint);
        assertTrue(result);

        result = complaintService.removeById(complaint.getComplaintId());
        assertTrue(result);
    }

}
