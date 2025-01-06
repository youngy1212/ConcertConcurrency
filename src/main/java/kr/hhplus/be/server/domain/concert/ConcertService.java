package kr.hhplus.be.server.domain.concert;

import kr.hhplus.be.server.domain.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertService {

    private final ConcertReader concertReader;

    public Concert getConcertById(long concertId) {
        return concertReader.findById(concertId)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND,"콘서트를 찾을 수 없습니다."));
    }

}
