package com.likelion.lionlib.service;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import com.likelion.lionlib.dto.CountReservationResponse;
import com.likelion.lionlib.dto.CustomUserDetails;
import com.likelion.lionlib.dto.ReservationRequest;
import com.likelion.lionlib.dto.ReservationResponse;
import com.likelion.lionlib.exception.ReservationAlreadyExistsException;
import com.likelion.lionlib.exception.ReservationNotFoundException;
import com.likelion.lionlib.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final GlobalService globalService;

    // 도서 예약 등록
    public ReservationResponse addReservation(CustomUserDetails customUserDetails, ReservationRequest request) {
        Member member = globalService.findMemberById(customUserDetails.getId());
        Book book = globalService.findBookById(request.getBookId());
        boolean reservationExists = reservationRepository.existsByMemberAndBook(member, book);
        if (reservationExists) {
            throw new ReservationAlreadyExistsException();
        }

        Reservation reservation = Reservation.builder()
                .member(member)
                .book(book)
                .build();
        reservationRepository.save(reservation);
        return ReservationResponse.fromEntity(reservation);
    }

    // 예약 정보 조회
    public ReservationResponse getReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ReservationNotFoundException());
        return ReservationResponse.fromEntity(reservation);
    }

    // 예약 취소
    public ReservationResponse deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ReservationNotFoundException());
        reservationRepository.delete(reservation);
        return ReservationResponse.fromEntity(reservation);
    }

    // 사용자 예약 목록 조회
    public List<Reservation> getReservationsByMember (CustomUserDetails customUserDetails) {
        Member member = globalService.findMemberById(customUserDetails.getId());
        return reservationRepository.findAllByMember(member);
    }

    // 도서 예약 수 현황 조회
    public CountReservationResponse countBookReservations (Long bookId) {
        Book book = globalService.findBookById(bookId);
        Long count = reservationRepository.countByBook(book);
        return new CountReservationResponse(count);
    }
}
