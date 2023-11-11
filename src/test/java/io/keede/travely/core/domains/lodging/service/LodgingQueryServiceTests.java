package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
* @author keede
* Created on 2023/10/21
*/
@ExtendWith(MockitoExtension.class)
public class LodgingQueryServiceTests {

    @Mock
    private LodgingRepository lodgingRepository;

    @InjectMocks
    private LodgingQueryService sut;

    @Test
    void 숙소_전체_조회() {

        given(lodgingRepository.findAll()).willReturn(new ArrayList<>());

        List<Lodging> lodgings = sut.getLodgings();

        then(lodgingRepository)
                .should(times(1))
                .findAll();

        assertThat(lodgings).isNotNull();

    }

}
