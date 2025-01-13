package kr.hhplus.be.server.application.dto;

import java.time.LocalDateTime;

public record QueueTokenDto(String queueTokenId,Long userId, Long concertId, LocalDateTime expiresAt) {}