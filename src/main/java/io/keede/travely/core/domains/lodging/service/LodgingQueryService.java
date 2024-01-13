package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.entity.Lodging;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author keede
* Created on 2023/10/21
*/
@Service
public class LodgingQueryService {

    private final LodgingReader lodgingReader;

    public LodgingQueryService(
            final LodgingReader lodgingReader
    ) {
        this.lodgingReader = lodgingReader;
    }

    public List<Lodging> getAll() {
        return this.lodgingReader.findLodgingAll();
    }
}
