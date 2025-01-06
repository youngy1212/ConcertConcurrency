package kr.hhplus.be.server.domain.concert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import kr.hhplus.be.server.domain.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Concert extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_id")
    private Long id;

    private String title;

    private LocalDateTime concertDate;

    @Builder
    private Concert(String title, LocalDateTime concertDate) {
        this.title = title;
        this.concertDate = concertDate;
    }

    public static Concert create(String title, LocalDateTime concertDate) {
        return Concert.builder()
                .title(title)
                .concertDate(concertDate).build();
    }

}
