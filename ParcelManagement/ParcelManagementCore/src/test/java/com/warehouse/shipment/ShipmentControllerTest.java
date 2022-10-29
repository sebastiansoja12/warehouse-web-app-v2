package com.warehouse.shipment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.shipment.domain.port.primary.ShipmentPort;
import com.warehouse.shipment.infrastructure.adapter.secondary.ShipmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.xml.transform.StringSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class ShipmentControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private ShipmentPort shipmentPort;

    private final ObjectMapper mapper = new ObjectMapper();

    private final ShipmentController shipmentController = new ShipmentController(shipmentPort);


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();

    }

    @Test
    void shouldCreateShipment() throws Exception {
        // given
        final StringSource requestJson = createShipmentRequest();
        final StringSource responseJson = createShipmentResponse();

        // when
        final MvcResult result = mockMvc.perform(post("/shipment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(requestJson)))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        // then
        final String message = result.getResponse().getContentAsString();
        assertThat(message).isEqualTo(responseJson.toString());
    }

    private StringSource createShipmentRequest() {
        final String request =  "{" +
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

        return new StringSource(request);
    }

    private StringSource createShipmentResponse() {
        final String response =
                "{" +
                "    \"paymentUrl\": \"https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-88191446AY558015M\"," +
                "    \"parcelId\": 1000000\n" +
                "}";

        return new StringSource(response);
    }

    @Test
    void shouldThrowException() {

    }

    @Test
    void shouldReturnParcelById() {

    }

    @Test
    void shouldThrowParcelNotFoundException() {

    }

    @Test
    void shouldNotCreateShipment() {

    }

}
