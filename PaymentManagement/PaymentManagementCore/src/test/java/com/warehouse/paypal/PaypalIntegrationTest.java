package com.warehouse.paypal;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.warehouse.paypal.configuration.PaypalTestConfiguration;
import com.warehouse.paypal.domain.model.LinkInformation;
import com.warehouse.paypal.domain.model.PaymentRequest;
import com.warehouse.paypal.domain.model.PaymentResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.xml.transform.StringSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PaypalTestConfiguration.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PaypalIntegrationTest {

    private MockMvc mockMvc;

    private final static Long PARCEL_ID = 100001L;

    private final static double PRICE = 25;

    @Test
    void shouldUpdatePayment() {
        // given

        // when

        // then
    }

    @Test
    void shouldCreatePayment() {
        // given

        // when


        // then
    }

    @Test
    void shouldThrowException() {
        // given

        // when

        // then
    }


    private StringSource getJsonPaymentResponse(PaymentResponse response) {
        final String json = "{" +
                "    \"link\": {" +
                "        \"paymentUrl\": " + response.getLink().getPaymentUrl() +
                "    }," +
                "    \"createTime\": " + response.getCreateTime() +
                "    \"failureReason\": " + response.getFailureReason() +
                "    \"paymentMethod\": " + response.getPaymentMethod() +
                "}";
        return new StringSource(json);
    }

    private StringSource getJsonPaymentRequest(PaymentRequest request) {
        final String json =
                "{" +
                        "    \"parcelId\":" + request.getParcelId() + "," +
                        "    \"price\": " + request.getPrice() + "\n" +
                        "}";

        return new StringSource(json);
    }

    private LinkInformation link() {
        final LinkInformation linkInformation = new LinkInformation();
        linkInformation.setPaymentUrl("test.pl");
        return linkInformation;
    }
}
