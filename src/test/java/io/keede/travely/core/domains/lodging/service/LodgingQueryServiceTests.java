package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.config.BusinessMockTestConfiguration;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
* @author keede
* Created on 2023/10/21
*/
@BusinessMockTestConfiguration
public class LodgingQueryServiceTests {

    @Mock
    private LodgingReader lodgingReader;

    private LodgingQueryService sut;

    @BeforeEach
    void setUp() {
        this.sut = new LodgingQueryService(
                this.lodgingReader
        );
    }

    @Test
    void 숙소_전체_조회() {

        given(lodgingReader.findLodgingAll())
                .willReturn(new ArrayList<>());

        List<Lodging> lodgings = sut.findAll();

        then(this.lodgingReader)
                .should(times(1))
                .findLodgingAll();

        assertThat(lodgings).isNotNull();

    }

}
