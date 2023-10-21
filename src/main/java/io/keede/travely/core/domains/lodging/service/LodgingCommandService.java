package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.springframework.stereotype.Service;

@Service
public class LodgingCommandService {

    private final LodgingRepository lodgingRepository;

    public LodgingCommandService(final LodgingRepository lodgingRepository) {
        this.lodgingRepository = lodgingRepository;
    }

    public void create(Lodging entity) {
        lodgingRepository.save(entity);
    }
}
