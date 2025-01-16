package kr.hhplus.be.server.api.reservation;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import kr.hhplus.be.server.api.reservation.dto.ReservationRequest;
import kr.hhplus.be.server.api.reservation.dto.TempReservationRequest;
import kr.hhplus.be.server.application.PaymentFacade;
import kr.hhplus.be.server.application.ReservationFacade;
import kr.hhplus.be.server.application.dto.ReservationDto;
import kr.hhplus.be.server.application.dto.TempReservationDto;
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
    void reservationSeatReturnTempReservationResponse() throws Exception {
        // given
        TempReservationRequest request = new TempReservationRequest(1L, 2L, 3L, "TOKEN_ID");
        TempReservationDto tempReservationDto = new TempReservationDto(20L, LocalDateTime.now().plusMinutes(10));

        when(reservationFacade.reserveTempSeat(anyLong(), anyLong(), anyLong(), anyString()))
                .thenReturn(tempReservationDto);

        // When & Then
        mockMvc.perform(post("/concert/seats/temp/reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tempReservationId").value(tempReservationDto.tempReservationId()))
                .andExpect(jsonPath("$.expiresAt").exists());

    }


    @DisplayName("결제를 완료하여 예약을 확정한다.")
    @Test
    void completeReservationReturnReservationResponse() throws Exception {
        // given
        ReservationRequest request = new ReservationRequest(1L, 2L,"TOKEN_ID",4L,5L,"Data");
        ReservationDto reservationDto = new ReservationDto(20L,1L, 2L);

        when(paymentFacade.completeReservation(anyLong(), anyLong(), anyLong(), anyString(),anyLong(), anyString()))
                .thenReturn(reservationDto);

        // When & Then
        mockMvc.perform(post("/reservation/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentTd").value(reservationDto.paymentId()))
                .andExpect(jsonPath("$.reservationTd").value(reservationDto.reservationId()))
                .andExpect(jsonPath("$.seatId").value(reservationDto.seatId()));


    }



}