package com.warehouse.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.warehouse.model.Depot;
import com.warehouse.warehouse.model.Parcel;
import com.warehouse.warehouse.repository.ParcelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import javax.transaction.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class ParcelControllerTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ParcelController parcelController;



    @Test
    @Transactional
    void shouldSaveParcel(){
        Parcel parcel = new Parcel();
        parcel.setFirstName("Sebastian");
        parcel.setLastName("Soja");
        parcel.setSenderTelephone("123456789");
        parcel.setSenderEmail("sebastian5152@wp.pl");
        parcel.setRecipientFirstName("Adam");
        parcel.setRecipientLastName("Soja");
        parcel.setRecipientTelephone("987654321");
        parcel.setRecipientCity("Rybnik");
        parcel.setRecipientStreet("Fajna 55");
        parcel.setRecipientEmail("adam12345@o2.pl");
        parcel.setCustom(false);
        parcelRepository.save(parcel);
    }

    @Test
    @Transactional
    void shouldGetSingleParcel() throws Exception {
        //given
        Parcel parcel = new Parcel();
        parcel.setFirstName("Sebastian");
        parcel.setLastName("Soja");
        parcel.setSenderTelephone("123456789");
        parcel.setSenderEmail("sebastian5152@wp.pl");
        parcel.setRecipientFirstName("Adam");
        parcel.setRecipientLastName("Soja");
        parcel.setRecipientTelephone("987654321");
        parcel.setRecipientCity("Rybnik");
        parcel.setRecipientStreet("Fajna 55");
        parcel.setRecipientEmail("adam12345@o2.pl");
        parcel.setCustom(false);
        parcelRepository.save(parcel);
        //when
        MvcResult mvcResult = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/api/parcels/" + parcel.getId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //then
        Parcel parcelToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Parcel.class);

        assertThat(parcelToTest).isNotNull();
        assertThat(parcelToTest.getFirstName()).isEqualTo(parcel.getFirstName());
        assertThat(parcelToTest.getId()).isEqualTo(parcel.getId());
        assertThat(parcelToTest.getRecipientCity()).isEqualTo(parcel.getRecipientCity());
    }

    @Test
    void shouldReturnAllParcels() throws Exception {
        MvcResult mvcResult =  mockMvc.perform(MockMvcRequestBuilders.get("/api/parcels"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        assertThat(mvcResult).isNotNull();
    }

    @Test
    void shouldGetParcelLabel(){

    }

}