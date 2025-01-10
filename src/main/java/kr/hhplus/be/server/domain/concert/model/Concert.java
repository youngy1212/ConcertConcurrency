package kr.hhplus.be.server.domain.concert.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String place;

    @Builder
    private Concert(String title, String place) {
        this.title = title;
        this.place = place;
    }

    public static Concert create(String title, String place) {
        return Concert.builder()
                .title(title)
                .place(place).build();
    }

}
