package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.fixture.LodgingFixture;
import io.keede.travely.core.exception.service.BusinessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * @author keede
 * Created on 2023/10/21
 */
@BusinessMockTestConfiguration
public class LodgingCommandServiceTests {

    @Mock
    private LodgingReader lodgingReader;
    @Mock
    private LodgingWriter lodgingWriter;

    private LodgingCommandService sut;

    @BeforeEach
    void setUp() {
        this.sut = new LodgingCommandService(
                this.lodgingReader,
                this.lodgingWriter
        );
    }

    @Test
    void 숙소생성_성공() {

        LodgingDto.Create create = LodgingFixture.createLodgingBuilder()
                .build();

        Lodging entity = create.toEntity();

        LodgingDto.Information information = entity.toInformation();

        given(this.lodgingWriter.save(
                any(Lodging.class))
        )
                .willReturn(entity);

        LodgingDto.Information result = sut.create(create);

        then(this.lodgingWriter)
                .should(times(1))
                .save(any(Lodging.class));

        assertThat(result.name()).isEqualTo(information.name());
    }

    @Test
    void 숙소삭제_성공() {

        LodgingDto.Remove remove = mock(LodgingDto.Remove.class);

        Lodging lodging = mock(Lodging.class);

        given(this.lodgingReader.findById(anyLong()))
                .willReturn(lodging);

        this.sut.remove(remove);

        then(this.lodgingReader).should(times(1))
                .findById(anyLong());
        then(lodging).should(times(1)).remove();
    }

    @Test
    void 숙소_조회_실패로_인한_삭제_기능_예외_발생() {

        LodgingDto.Remove remove = mock(LodgingDto.Remove.class);

        Lodging mock = mock(Lodging.class);

        given(this.lodgingReader.findById(anyLong()))
                .willThrow(BusinessException.class);

        Assertions.assertThrows(
                BusinessException.class,
                () -> this.sut.remove(remove)
        );

        then(mock).should(times(0)).remove();
    }
}
