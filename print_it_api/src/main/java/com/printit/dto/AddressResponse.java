package com.printit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressResponse {
	private long id;
	private String name;
	private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int pinCode;
    private String country;
    private long phone;
    private String addressType;

}
