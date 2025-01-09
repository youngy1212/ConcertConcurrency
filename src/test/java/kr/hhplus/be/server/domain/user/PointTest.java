package kr.hhplus.be.server.domain.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

    @DisplayName("잔액 충전에 성공합니다.")
    @Test
    void testCharge() {
        // given
        User user = User.create("유저1", "이메일");
        Point point = Point.create(10000L,user);

        // when
        point.charge(2000L);

        // then
        assertEquals(12000L, point.getAmount());

    }
}