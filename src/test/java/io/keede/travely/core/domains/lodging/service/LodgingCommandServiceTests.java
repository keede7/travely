package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
* @author keede
* Created on 2023/10/21
*/
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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

        LodgingDto.Create create = new LodgingDto.Create(lodgingName, maxUserCount, address, from, to);
        Lodging entity = create.toEntity();

        LodgingDto.Information information = entity.toInformation();

        given(this.lodgingRepository.save(
                any(Lodging.class))
        )
                .willReturn(entity);

        LodgingDto.Information result = sut.create(create);

        then(this.lodgingRepository)
                .should(times(1))
                .save(any(Lodging.class));

        assertThat(result.name()).isEqualTo(information.name());

    }

    @Test
    void 숙소삭제_성공() {

        LodgingDto.Remove remove = mock(LodgingDto.Remove.class);

        Lodging mock = mock(Lodging.class);

        given(this.lodgingRepository.findById(anyLong()))
                .willReturn(Optional.of(mock));

        this.sut.remove(remove);

        then(this.lodgingRepository).should(times(1))
                .findById(anyLong());
        then(mock).should(times(1)).remove();
    }
}
