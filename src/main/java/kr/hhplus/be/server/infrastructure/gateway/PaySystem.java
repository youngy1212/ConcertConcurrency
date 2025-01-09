package kr.hhplus.be.server.infrastructure.gateway;

import org.springframework.stereotype.Component;

@Component
public class PaySystem {

    public static boolean pay(Long amount) {
        return true; //외부 모듈 성공한다고 가정
    }

    public static boolean payFail(Long amount){
        return false; //실패한다고 가정
    }
}
