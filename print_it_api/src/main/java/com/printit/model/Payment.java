package com.printit.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "order_id",referencedColumnName = "id")
	private Order order;
	
	@Column(nullable = false)
	private String paymentMethod;
	@Column(nullable = false,unique = true)
    private String transactionId;
    private String status;
    private LocalDateTime paymentDateAndTime;
    private double amount;

}
