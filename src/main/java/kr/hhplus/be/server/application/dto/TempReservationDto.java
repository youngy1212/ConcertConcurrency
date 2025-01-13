package kr.hhplus.be.server.application.dto;

import java.time.LocalDateTime;

public record TempReservationDto (long tempReservationId, LocalDateTime expiresAt ){ }
