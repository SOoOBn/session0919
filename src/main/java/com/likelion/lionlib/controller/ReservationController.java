package com.likelion.lionlib.controller;

import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.dto.CountReservationResponse;
import com.likelion.lionlib.dto.ReservationRequest;
import com.likelion.lionlib.dto.ReservationResponse;
import com.likelion.lionlib.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReservationController {
    private final ReservationService reservationService;

    // 도서 예약 등록
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> addReservation(@RequestBody ReservationRequest reservationRequest) {
        log.info("Request POST a reservation: {}", reservationRequest);
        ReservationResponse savedReservation = reservationService.addReservation(reservationRequest);
        log.info("Response POST a reservation: {}", savedReservation);
        return ResponseEntity.ok(savedReservation);
    }

    // 예약 정보 조회
    @GetMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable("reservationId") Long reservationId) {
        log.info("Request GET a reservation with ID: {}", reservationId);
        ReservationResponse response = reservationService.getReservation(reservationId);
        return ResponseEntity.ok(response);
    }

    // 예약 취소
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<ReservationResponse> deleteReservation(@PathVariable("reservationId") Long reservationId) {
        log.info("Request DELETE reservation with ID: {}", reservationId);
        ReservationResponse response = reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok(response);
    }

    // 사용자 예약 목록 조회
    @GetMapping("/members/{memberId}/reservations")
    public ResponseEntity<List<ReservationResponse>> getReservationsByMember(@PathVariable("memberId") Long memberId) {
        log.info("Request GET Reservations with memberId: {}", memberId);
        List<Reservation> reservations = reservationService.getReservationsByMember(memberId);
        List<ReservationResponse> responseList = reservations.stream()
                .map(ReservationResponse::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // 도서 예약 수 현황 조회
    @GetMapping("/books/{bookId}/reservations")
    public ResponseEntity<CountReservationResponse> countBookReservations(@PathVariable("bookId") Long bookId) {
        log.info("Request GET the count of reservations with bookId: {}", bookId);
        CountReservationResponse response = reservationService.countBookReservations(bookId);
        return ResponseEntity.ok(response);
    }

}
