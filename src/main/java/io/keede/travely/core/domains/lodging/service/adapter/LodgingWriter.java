package io.keede.travely.core.domains.lodging.service.adapter;


import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author keede
* Created on 2024/01/13
*/
@Service
@Transactional
public class LodgingWriter {

    private final LodgingRepository lodgingRepository;

    public LodgingWriter(
            final LodgingRepository lodgingRepository
    ) {
        this.lodgingRepository = lodgingRepository;
    }

    public Lodging save(Lodging entity) {
        return this.lodgingRepository.save(entity);
    }

}
