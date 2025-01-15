package kr.hhplus.be.server.domain.concert.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import kr.hhplus.be.server.api.concert.dto.ConcertDateResponse;
import kr.hhplus.be.server.api.concert.dto.SeatResponse;
import kr.hhplus.be.server.domain.concert.model.Concert;
import kr.hhplus.be.server.domain.concert.model.ConcertSchedule;
import kr.hhplus.be.server.domain.concert.repository.ConcertQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertQueryService {

    private final ConcertQuery concertQuery;

    public Concert getConcertById(long concertId) {
        return concertQuery.findById(concertId)
                .orElseThrow(() -> new NoSuchElementException("콘서트를 찾을 수 없습니다."));
    }

    public List<ConcertDateResponse> getAllConcertSchedule(final long concertId) {

        List<ConcertSchedule> schedules = concertQuery.findAllByConcertId(concertId);

        if(schedules.isEmpty()){
            throw new NoSuchElementException("콘서트의 예약 가능한 날을 찾을 수 없습니다.");
        }

        return schedules.stream().map(ConcertDateResponse::of)
                .collect(Collectors.toList());
    }

    public SeatResponse getConcertSeats(final long concertScheduleId) {
        List<Long> seatIds = concertQuery.findByConcertScheduleId(concertScheduleId);

        if(seatIds.isEmpty()){
            throw new NoSuchElementException("콘서트의 좌석을 찾을 수 없습니다.");
        }

        return SeatResponse.of(seatIds);
    }

    public ConcertSchedule getConcertSchedule(final long concertScheduleId){
        return concertQuery.getConcertSchedule(concertScheduleId).orElseThrow(() -> new NoSuchElementException("콘서트 일정을 찾을 수 없습니다."));
    }



}