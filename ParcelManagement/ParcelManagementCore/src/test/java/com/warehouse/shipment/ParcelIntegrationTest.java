package com.warehouse.shipment;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ParcelIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Disabled
    void shouldCreateShipment() throws Exception {
        // given
        final String request = requestParcel();
        // when
        final MvcResult send = mockMvc.perform(post("/shipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        assertThat(send).isNotNull();
    }

    private String requestParcel() {
        return "{" +
                "  \"parcel\": {" +
                "    \"sender\": {" +
                "      \"firstName\": \"asdasdsa\"," +
                "      \"lastName\": \"asdasd\",\n" +
                "      \"email\": \"asdasds@wa.pl\",\n" +
                "      \"telephoneNumber\": \"asdasdsa\",\n" +
                "      \"city\": \"asdasdas\",\n" +
                "      \"postalCode\": \"00-000\",\n" +
                "      \"street\": \"asdsadsa\"\n" +
                "    },\n" +
                "    \"recipient\": {\n" +
                "      \"firstName\": \"asdasdsa\",\n" +
                "      \"lastName\": \"asdsadasd\",\n" +
                "      \"email\": \"asdasd@wp.pl\",\n" +
                "      \"telephoneNumber\": \"asdasdas\",\n" +
                "      \"city\": \"asdasdas\",\n" +
                "      \"postalCode\": \"sadsa\",\n" +
                "      \"street\": \"asdsad\"\n" +
                "    },\n" +
                "    \"parcelType\": \"TINY\"\n" +
                "  }\n" +
                "}";
    }
}
