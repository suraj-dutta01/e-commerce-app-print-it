package com.printit.dto;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import com.printit.model.Address;
import com.printit.model.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
	private long id;
	private String userName;
	private String status;
    private double totalPrice;
    private LocalDateTime orderDateAndTime;
    private LocalDate deliveryDate;
    private List<OrderDetail> orderDetails;
    private String paymentStatus;
    private Address address;
    

}
