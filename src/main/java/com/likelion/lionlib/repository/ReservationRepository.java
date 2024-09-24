package com.likelion.lionlib.repository;

import com.likelion.lionlib.domain.Book;
import com.likelion.lionlib.domain.Member;
import com.likelion.lionlib.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 사용자 예약 목록 조회
    List<Reservation> findAllByMember(Member member);

    // 도서 예약 수 현황 조회
    Long countByBook(Book book);

}
