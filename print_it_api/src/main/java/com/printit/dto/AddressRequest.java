package com.printit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequest {
	@NotBlank(message = "Name is Mendatory")
	private String name;
	@NotBlank(message = "addressLine1 is Mendatory")
	private String addressLine1;
    private String addressLine2;
    @NotBlank(message = "City is Mendatory")
    private String city;
    @NotBlank(message = "State is Mendatory")
    private String state;
    private int pinCode;
    @NotBlank(message = "Country is Mendatory")
    private String country;
    private long phone;
    @NotBlank(message = "Address Type is Mendatory")
    private String addressType;

}
