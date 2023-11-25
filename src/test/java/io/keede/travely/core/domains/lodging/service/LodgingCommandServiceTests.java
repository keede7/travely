package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.domains.lodging.fixture.LodgingFixture;
import io.keede.travely.core.exception.service.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * @author keede
 * Created on 2023/10/21
 */
@BusinessMockTestConfiguration
public class LodgingCommandServiceTests {

    @Mock
    private LodgingRepository lodgingRepository;

    private LodgingCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new LodgingCommandService(
                this.lodgingRepository
        );
    }

    @Test
    void 숙소생성_성공() {

        LodgingDto.Create create = LodgingFixture.createLodgingBuilder()
                .build();

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

    @Test
    void 숙소_조회_실패로_인한_삭제_기능_예외_발생() {

        LodgingDto.Remove remove = mock(LodgingDto.Remove.class);

        Lodging mock = mock(Lodging.class);

        given(this.lodgingRepository.findById(anyLong()))
                .willThrow(BusinessException.class);

        Assertions.assertThrows(
                BusinessException.class,
                () -> this.sut.remove(remove)
        );
        then(mock).should(times(0)).remove();

    }
}
