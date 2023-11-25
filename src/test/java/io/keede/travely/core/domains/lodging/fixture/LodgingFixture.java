package io.keede.travely.core.domains.lodging.fixture;

import io.keede.travely.core.domains.lodging.dto.LodgingDto;

import java.time.LocalDateTime;

/**
* @author keede
* Created on 2023/11/19
*/
public final class LodgingFixture {

    private static final String NAME = "테스트숙소";

    private static final Integer MAX_USER_COUNT = 7;

    private static final String ADDRESS = "경기도 부천시 원미구";

    private static final LocalDateTime START =
            LocalDateTime.of(
                    2023, 9, 10, 0, 0, 0
            );

    private static final LocalDateTime END =
            LocalDateTime.of(
                    2023, 9, 12, 0, 0, 0
            );

    public static CreateBuilder createLodgingBuilder() {
        return new CreateBuilder();
    }

    public static final class CreateBuilder {
        private String name;
        private Integer maxUserCount;
        private String address;
        private LocalDateTime from;
        private LocalDateTime to;

        public CreateBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public CreateBuilder maxUserCount(final int maxUserCount) {
            this.maxUserCount = maxUserCount;
            return this;
        }

        public CreateBuilder address(final String address) {
            this.address = address;
            return this;
        }

        public CreateBuilder from(final LocalDateTime from) {
            this.from = from;
            return this;
        }

        public CreateBuilder to(final LocalDateTime to) {
            this.to = to;
            return this;
        }

        public LodgingDto.Create build() {
            return new LodgingDto.Create(
                    this.name == null ? NAME : this.name,
                    this.maxUserCount == null ? MAX_USER_COUNT : this.maxUserCount,
                    this.address == null ? ADDRESS : this.address,
                    this.from == null ? START : this.from,
                    this.to == null ? END : this.to
            );
        }

    }
}
