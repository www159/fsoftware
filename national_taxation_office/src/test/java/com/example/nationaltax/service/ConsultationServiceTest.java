package com.example.nationaltax.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.nationaltax.bean.Consultation;
import com.example.nationaltax.util.MockObject;

@SpringBootTest
public class ConsultationServiceTest {
    @Autowired
    private ConsultationService consultationService;

    

    @Test
    public void saveTest() {
        Consultation consultation = MockObject.mockConsultation("some");

        boolean result = consultationService.save(consultation);

        assertTrue(result);
    }

    @Test
    public void getByIdTest() {
        Consultation consultation = MockObject.mockConsultation("get by id");

        consultationService.save(consultation);

        Consultation result = consultationService.getById(consultation.getConsultationId());

        assertTrue(result.getName().equals(new String("get by id name")));
    }

    @Test
    public void updateByIdTest() {
        Consultation consultation = MockObject.mockConsultation("update by id");

        consultationService.save(consultation);
        consultation.setName("updated");

        boolean result = consultationService.updateById(consultation);

        assertTrue(result);
        consultation = consultationService.getById(consultation.getConsultationId());
        assertTrue(consultation.getName().equals(new String("updated")));
    }

    @Test
    public void removeByIdTest() {
        Consultation consultation = MockObject.mockConsultation("remove by id");

        boolean result = consultationService.save(consultation);
        assertTrue(result);

        result = consultationService.removeById(consultation.getConsultationId());
        assertTrue(result);
    }
}
