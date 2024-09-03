package com.sprrestaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sprrestaurant.dtos.ReservationDto;
import com.sprrestaurant.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String tableType;

    private  String description;

    private  String dateTime;

    private ReservationStatus reservationStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public ReservationDto getReservationDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(id);
        reservationDto.setTableType(tableType);
        reservationDto.setReservationStatus(reservationStatus);
        reservationDto.setDescription(description);
        reservationDto.setDateTime(dateTime);
        reservationDto.setCustomerId(user.getId());
        return reservationDto;
    }
}
