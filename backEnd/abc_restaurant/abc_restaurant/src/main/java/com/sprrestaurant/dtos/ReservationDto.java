package com.sprrestaurant.dtos;


import com.sprrestaurant.enums.ReservationStatus;
import lombok.Data;

@Data
public class ReservationDto {

    private Long id;

    private  String tableType;

    private  String description;

    private  String dateTime;

    private Long customerId;

    private ReservationStatus reservationStatus;

    private String customerName;




}
