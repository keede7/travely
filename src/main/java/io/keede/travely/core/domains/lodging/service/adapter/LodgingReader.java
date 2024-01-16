package io.keede.travely.core.domains.lodging.service.adapter;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.entity.LodgingRepository;
import io.keede.travely.core.exception.ErrorResponse;
import io.keede.travely.core.exception.service.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author keede
* Created on 2024/01/13
*/
@Service
@Transactional(readOnly = true)
public class LodgingReader {

    private final LodgingRepository lodgingRepository;

    public LodgingReader(
            final LodgingRepository lodgingRepository
    ) {
        this.lodgingRepository = lodgingRepository;
    }

    public List<Lodging> findLodgingAll() {
        return lodgingRepository.findLodgingAll();
    }

    public Lodging findById(Long lodgingId) {
        return this.lodgingRepository.findLodgingById(lodgingId)
                .orElseThrow(
                        () -> new BusinessException(
                                ErrorResponse.COMMON,
                                String.format("to find Id: %d", lodgingId)
                        )
                );
    }
}
