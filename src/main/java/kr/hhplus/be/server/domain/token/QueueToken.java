package kr.hhplus.be.server.domain.token;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;
import kr.hhplus.be.server.domain.common.entity.BaseEntity;
import kr.hhplus.be.server.domain.common.exception.CustomException;
import kr.hhplus.be.server.domain.concert.Concert;
import kr.hhplus.be.server.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "user_concert",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_user_concert",
                        columnNames = {"user_id", "concert_id"}
                )
        }
)
public class QueueToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String queueTokenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concert_id")
    private Concert concert;

    private LocalDateTime expiresAt;

    private LocalDateTime enqueuedAt; //대기열에 진입하는 시간

    @Enumerated(EnumType.STRING)
    private QueueTokenStatus status;


    @Builder
    public QueueToken(String queueTokenId, User user, Concert concert, LocalDateTime expiresAt,
                      LocalDateTime enqueuedAt,
                      QueueTokenStatus status) {
        this.queueTokenId = queueTokenId;
        this.user = user;
        this.concert = concert;
        this.expiresAt = expiresAt;
        this.enqueuedAt = enqueuedAt;
        this.status = status;
    }

    public static QueueToken create(User user, Concert concert) {
        return QueueToken.builder()
                .queueTokenId(UUID.randomUUID().toString())
                .user(user)
                .concert(concert)
                .enqueuedAt(LocalDateTime.now())
                .status(QueueTokenStatus.PENDING)
                .build();
    }

    //토큰 활성화
    public void tokenActive(LocalDateTime localDateTime){
        if(status == QueueTokenStatus.PENDING || status == QueueTokenStatus.EXPIRED){
            status = QueueTokenStatus.ACTIVE;
            expiresAt = localDateTime.plusMinutes(10);
        }else{
            throw new CustomException("이미 예약중입니다.");
        }
    }

    //토큰 만료
    public void tokenExpire(){
        if (status == QueueTokenStatus.ACTIVE || status == QueueTokenStatus.PENDING) {
            status = QueueTokenStatus.EXPIRED;
        } else {
            throw new CustomException("토큰을 만료시킬 수 없습니다.");
        }
    }

    //토크 만료 확인
    public boolean isExpired(){
        return expiresAt != null && expiresAt.isBefore(LocalDateTime.now());
    }

}
