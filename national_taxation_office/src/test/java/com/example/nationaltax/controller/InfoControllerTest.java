package com.example.nationaltax.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.nationaltax.bean.Info;
import com.example.nationaltax.util.Converter;
import com.example.nationaltax.util.MockObject;
import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootTest
@AutoConfigureMockMvc
public class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private Logger logger = LoggerFactory.getLogger(InfoControllerTest.class);

    private List<Info> getInfosByTitle(String title) throws Exception {
        List<Info> infos = Converter.json2obj(mockMvc
                .perform(MockMvcRequestBuilders.get(String.format("/info/list?title=%s", title)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(),
                new TypeReference<List<Info>>() {
                });

        logger.info("infos are: {}", infos);

        return infos;

    }

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addInfoTest() throws Exception {
        Info info = MockObject.mockInfo("add info");
        mockMvc.perform(MockMvcRequestBuilders.post("/info").contentType(MediaType.APPLICATION_JSON)
                .content(Converter.obj2json(info)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getInfoAndListInfoTest() throws Exception {
        Info info = MockObject.mockInfo("get info by id");

        mockMvc.perform(MockMvcRequestBuilders.post("/info").contentType(MediaType.APPLICATION_JSON)
                .content(Converter.obj2json(info)))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        List<Info> infos = getInfosByTitle(info.getTitle());

        assertNotNull(infos.get(0));
        assertTrue(infos.get(0).getTitle().equals("get info by id title"));
    }

    @Test
    public void putInfoAndDeleteTest() throws Exception {
        Info info = MockObject.mockInfo("put and delete");

        mockMvc.perform(MockMvcRequestBuilders.post("/info").contentType(MediaType.APPLICATION_JSON)
                .content(Converter.obj2json(info)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("put and delete title")).andReturn();

        List<Info> infos = getInfosByTitle(info.getTitle());

        assertNotNull(infos.get(0));
        
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/info/%d", infos.get(0).getInfoId()))).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
