package io.keede.travely.core.domains.lodging.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.keede.travely.core.domains.lodging.entity.Lodging;

import java.time.LocalDateTime;

/**
* @author keede
* Created on 2023/10/21
*/
public class LodgingDto {

    public record Create(
            String name,
            Integer maxUserCount,
            String address,
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul", shape = JsonFormat.Shape.STRING)
            LocalDateTime from,
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul", shape = JsonFormat.Shape.STRING)
            LocalDateTime to
    ) {
        public Lodging toEntity() {
            return Lodging.of(
                    this.name,
                    this.maxUserCount,
                    this.address,
                    this.from,
                    this.to
            );
        }
    }

    public record Information(
            Long id,
            String name,
            Integer maxUserCount,
            String address,
            String from,
            String to
    ) {
    }

    public record Remove(
            Long id
    ) {
    }
}
