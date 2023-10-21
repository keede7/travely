package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class LodgingCommandServiceTests {

    @Mock
    private LodgingRepository lodgingRepository;

    @InjectMocks
    private LodgingCommandService sut;

    @Test
    void 숙소생성_성공() {

        final String lodgingName = "숙소1";
        final int maxUserCount = 7;
        final String address = " 경기도 부천시 원미구";
        final LocalDateTime from = LocalDateTime.of(2023, 9, 10, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2023, 9, 12, 0, 0, 0);

        Lodging entity = Lodging.of(lodgingName, maxUserCount, address, from, to);

        given(this.lodgingRepository.save(entity)).willReturn(entity);

        sut.create(entity);

        then(this.lodgingRepository).should(times(1)).save(entity);

    }

}
