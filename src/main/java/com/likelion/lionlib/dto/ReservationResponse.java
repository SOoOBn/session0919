package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.Reservation;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReservationResponse {
    private Long memberId;
    private Long bookId;


    public static ReservationResponse fromEntity(Reservation reservation) {
        return ReservationResponse.builder()
                .memberId(reservation.getMember().getId())
                .bookId(reservation.getBook().getId())
                .build();
    }
}
