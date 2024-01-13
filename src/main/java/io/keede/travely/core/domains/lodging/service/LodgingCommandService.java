package io.keede.travely.core.domains.lodging.service;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;
import io.keede.travely.core.domains.lodging.entity.Lodging;
import io.keede.travely.core.domains.lodging.service.adapter.LodgingReader;
import io.keede.travely.core.domains.lodging.service.adapter.LodgingWriter;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
* @author keede
* Created on 2023/10/21
*/
@Service
public class LodgingCommandService {

    private final LodgingReader lodgingReader;
    private final LodgingWriter lodgingWriter;

    public LodgingCommandService(
            final LodgingReader lodgingReader,
            final LodgingWriter lodgingWriter
    ) {
        this.lodgingReader = lodgingReader;
        this.lodgingWriter = lodgingWriter;
    }

    @PostConstruct
    @Transactional
    public void init() {

        Lodging lodging = new Lodging(
                "가평펜션",
                10,
                "경기도 가평",
                LocalDateTime.MIN,
                LocalDateTime.MAX
        );

        this.lodgingWriter.save(lodging);
    }

    @Transactional
    public LodgingDto.Information create(LodgingDto.Create create) {

        Lodging entity = create.toEntity();

        this.lodgingWriter.save(entity);

        return entity.toInformation();
    }

    @Transactional
    public void remove(LodgingDto.Remove remove) {

        final Long removeId = remove.id();

        Lodging entity = this.lodgingReader.findById(removeId);

        entity.remove();

    }
}
