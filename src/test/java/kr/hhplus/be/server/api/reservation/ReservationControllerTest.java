package kr.hhplus.be.server.api.reservation;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.api.reservation.dto.PaymentReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.ReservationRequest;
import kr.hhplus.be.server.application.PaymentFacade;
import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.application.dto.PaymentReservationDto;
import kr.hhplus.be.server.application.dto.ReservationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ReservationFacade reservationFacade;

    @MockitoBean
    private PaymentFacade paymentFacade;

    @DisplayName("좌석 예약을 성공적으로 수행한다.")
    @Test
    void reservationSeatReturnReservationResponse() throws Exception {
        // given
        ReservationRequest request = new ReservationRequest(1L, 2L, 3L, "TOKEN_ID");
        ReservationDto ReservationDto = new ReservationDto(20L, 21L);

        when(reservationFacade.reserveSeat(anyLong(), anyLong(), anyLong(), anyString()))
                .thenReturn(ReservationDto);

        // When & Then
        mockMvc.perform(post("/concert/seats/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reservationId").value(ReservationDto.reservationId()))
                .andExpect(jsonPath("$.seatId").value(ReservationDto.seatId()));

    }


    @DisplayName("결제를 완료하여 예약을 확정한다.")
    @Test
    void completeReservationReturnReservationResponse() throws Exception {
        // given
        PaymentReservationRequest request = new PaymentReservationRequest(1L, 2L,"TOKEN_ID",4L,5L,"Data");
        PaymentReservationDto paymentReservationDto = new PaymentReservationDto(20L,1L,2L,3L,4000);

        when(paymentFacade.completeReservation(anyLong(), anyLong(), anyLong(), anyString(),anyLong(), anyString()))
                .thenReturn(paymentReservationDto);

        // When & Then
        mockMvc.perform(post("/reservation/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.concertScheduleId").value(paymentReservationDto.concertScheduleId()))
                .andExpect(jsonPath("$.seatId").value(paymentReservationDto.seatId()))
                .andExpect(jsonPath("$.amount").value(paymentReservationDto.amount()))
                .andExpect(jsonPath("$.paymentId").value(paymentReservationDto.paymentId()));


    }



}