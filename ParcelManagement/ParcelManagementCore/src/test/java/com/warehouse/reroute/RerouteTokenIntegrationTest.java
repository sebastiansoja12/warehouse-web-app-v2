package com.warehouse.reroute;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.warehouse.reroute.configuration.RerouteTokenTestConfiguration;
import com.warehouse.reroute.domain.enumeration.ParcelType;
import com.warehouse.reroute.domain.exception.ParcelNotFoundException;
import com.warehouse.reroute.domain.model.*;
import com.warehouse.reroute.domain.port.secondary.ParcelPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenPort;
import com.warehouse.reroute.domain.port.secondary.RerouteTokenRepository;
import com.warehouse.reroute.domain.vo.ParcelResponse;
import com.warehouse.reroute.domain.vo.Recipient;
import com.warehouse.reroute.domain.vo.Sender;
import com.warehouse.reroute.infrastructure.adapter.secondary.exception.RerouteTokenNotFoundException;
import com.warehouse.reroute.infrastructure.adapter.secondary.exception.WarehouseMailException;
import com.warehouse.reroute.infrastructure.api.RerouteService;
import com.warehouse.reroute.infrastructure.api.dto.EmailDto;
import com.warehouse.reroute.infrastructure.api.dto.ParcelId;
import com.warehouse.reroute.infrastructure.api.dto.RerouteRequestDto;
import com.warehouse.reroute.infrastructure.api.dto.RerouteResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = RerouteTokenTestConfiguration.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RerouteTokenIntegrationTest {

    @Autowired
    private RerouteService rerouteService;

    @Autowired
    private RerouteTokenRepository rerouteTokenRepository;

    @Autowired
    private RerouteTokenPort rerouteTokenPort;

    @Autowired
    private ParcelPort parcelPort;

    private final static Long PARCEL_ID = 100001L;


    private final static Long INVALID_PARCEL_ID = 10L;


    private final static Integer VALID_TOKEN = 12345;


    private final static Integer INVALID_TOKEN = 0;


    private final static String EMAIL = "test@test.pl";

    @Test
    void shouldSendRequest() {
        shouldSendRerouteRequest();
    }

    @Test
    void shouldNotSendRequest() {
        shouldThrowExceptionWhenParcelIdIsEmpty();
    }

    @Test
    @DatabaseSetup("/dataset/rerouteToken.xml")
    void shouldSendUpdateParcelRequest() {
        shouldUpdateParcelRequestWhenTokenAndParcelExistInDb();
    }

    @Test
    @DatabaseSetup("/dataset/rerouteToken.xml")
    void shouldThrowRerouteTokenNotFoundException() {
        shouldNotUpdateParcelRequestWhenTokenDoesntExistInDb();
    }

    @Test
    @DatabaseSetup("/dataset/rerouteToken.xml")
    void shouldThrowParcelNotFoundException() {
        shouldNotUpdateParcelRequestWhenParcelDoesntExistInDb();
    }

    void shouldNotUpdateParcelRequestWhenParcelDoesntExistInDb() {
        // given
        final UpdateParcelRequest updateParcelRequest = UpdateParcelRequest.builder()
                .parcel(createParcel())
                .id(INVALID_PARCEL_ID)
                .token(VALID_TOKEN)
                .build();

        final Token token = Token.builder()
                .value(VALID_TOKEN)
                .build();
        // when
        final Executable executable = () -> parcelPort.update(updateParcelRequest);

        final ParcelNotFoundException parcelNotFoundException =
                assertThrows(ParcelNotFoundException.class, executable);
        // then
        assertAll(
                () -> assertThat(parcelNotFoundException.getMessage())
                        .isEqualTo("Parcel was not found")
        );
    }


    void shouldNotUpdateParcelRequestWhenTokenDoesntExistInDb() {
        // given
        final UpdateParcelRequest updateParcelRequest = UpdateParcelRequest.builder()
                .parcel(createParcel())
                .id(PARCEL_ID)
                .token(INVALID_TOKEN)
                .build();

        final Token token = Token.builder()
                .value(INVALID_TOKEN)
                .build();
        // when
        final Executable executable = () -> parcelPort.update(updateParcelRequest);

        final RerouteTokenNotFoundException rerouteTokenNotFoundException =
                assertThrows(RerouteTokenNotFoundException.class, executable);
        // then
        assertAll(
                () -> assertThat(rerouteTokenNotFoundException.getMessage())
                        .isEqualTo("Reroute token was not found")
        );
    }

    void shouldUpdateParcelRequestWhenTokenAndParcelExistInDb() {
        // given
        final UpdateParcelRequest updateParcelRequest = UpdateParcelRequest.builder()
                .parcel(createParcel())
                .id(PARCEL_ID)
                .token(VALID_TOKEN)
                .build();

        final Token token = Token.builder()
                .value(VALID_TOKEN)
                .build();
        // when
        final ParcelResponse response = parcelPort.update(updateParcelRequest);
        // and: we will check if given token exists in db
        final RerouteToken rerouteToken = rerouteTokenRepository.findByToken(token);
        // then
        assertAll(
                () -> assertThat(response.getSender().getFirstName()).isEqualTo("updatedTest"),
                () -> assertThat(rerouteToken.getToken().intValue()).isEqualTo(VALID_TOKEN)
        );
    }

    void shouldSendRerouteRequest() {
        // given
        final EmailDto emailDto = new EmailDto();
        emailDto.setValue(EMAIL);

        final ParcelId parcelId = new ParcelId();
        parcelId.setValue(PARCEL_ID);

        final RerouteRequestDto requestDto = new RerouteRequestDto();
        requestDto.setEmail(emailDto);
        requestDto.setParcelId(parcelId);

        // when
        final RerouteResponseDto response = rerouteService.sendReroutingInformation(requestDto);
        //then
        assertThat(response.getToken().intValue()).isNotNull();

    }

    void shouldThrowExceptionWhenParcelIdIsEmpty() {
        // given
        final EmailDto emailDto = new EmailDto();
        emailDto.setValue("");

        final ParcelId parcelId = new ParcelId();

        final RerouteRequestDto requestDto = new RerouteRequestDto();
        requestDto.setEmail(emailDto);
        requestDto.setParcelId(parcelId);

        final String exceptionMessage = "parcelId is marked non-null but is null";

        // when
        final Executable executable = () -> rerouteService.sendReroutingInformation(requestDto);

        final NullPointerException exception = assertThrows(NullPointerException.class, executable);
        //then
        assertThat(exception.getMessage()).isEqualTo(exceptionMessage);

    }


    private Parcel createParcel() {
        return Parcel.builder()
                .recipient(createRecipient())
                .parcelType(ParcelType.TEST)
                .sender(createSender())
                .build();
    }

    private Recipient createRecipient() {
        return Recipient.builder()
                .firstName("test")
                .lastName("test")
                .city("test")
                .street("test")
                .postalCode("00-000")
                .telephoneNumber("123")
                .email("test@test.pl")
                .build();
    }

    private Sender createSender() {
        return Sender.builder()
                .firstName("updatedTest")
                .lastName("test")
                .city("test")
                .street("test")
                .postalCode("00-000")
                .telephoneNumber("123")
                .email("test@test.pl")
                .build();
    }

}
