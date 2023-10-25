package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author keede
* Created on 2023/10/21
*/
@Service
public class LodgingQueryService {

    private final LodgingRepository lodgingRepository;

    public LodgingQueryService(final LodgingRepository lodgingRepository) {
        this.lodgingRepository = lodgingRepository;
    }

    public List<Lodging> getLodgings() {
        return lodgingRepository.findAll();
    }
}
