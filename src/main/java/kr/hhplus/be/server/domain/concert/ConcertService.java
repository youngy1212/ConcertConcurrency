package kr.hhplus.be.server.domain.concert;

import java.util.List;
import java.util.stream.Collectors;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
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

    public List<ConcertDateResponse> getConcertSchedule(final long concertId) {

        List<ConcertSchedule> schedules = concertReader.findByConcertId(concertId);

        if(schedules.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "콘서트의 예약 가능한 날을 찾을 수 없습니다.");
        }

        return schedules.stream().map(ConcertDateResponse::of)
                .collect(Collectors.toList());
    }




}
