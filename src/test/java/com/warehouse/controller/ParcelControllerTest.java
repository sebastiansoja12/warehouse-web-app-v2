package com.warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.enumeration.ParcelType;
import com.warehouse.entity.Parcel;
import com.warehouse.repository.ParcelRepository;
import com.warehouse.repository.PaymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
 This test will be changed in future tasks
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ParcelControllerTest {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @Transactional
    void shouldSaveParcel(){
        Parcel parcel = new Parcel();
        parcel.setParcelType(ParcelType.TINY);
        parcel.setPrice(parcel.getParcelType().getPrice());
        parcelRepository.save(parcel);
    }

    @Test
    @Transactional
    void shouldGetSingleParcel() throws Exception {
        // will be implemented again in the next stories
        //given
        Parcel parcel = new Parcel();
        parcelRepository.save(parcel);
        //when
        MvcResult mvcResult = (MvcResult) mockMvc.perform(MockMvcRequestBuilders.get("/api/parcels/" + parcel.getId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
        //then
        Parcel parcelToTest = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Parcel.class);

        assertThat(parcelToTest).isNotNull();
        assertThat(parcelToTest.getId()).isEqualTo(parcel.getId());
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