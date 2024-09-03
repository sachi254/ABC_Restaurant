package com.sprrestaurant.repositories;

import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(Long customerId);
}
