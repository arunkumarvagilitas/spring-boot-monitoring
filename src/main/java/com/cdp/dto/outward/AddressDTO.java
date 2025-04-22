package com.cdp.dto.outward;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDTO {
    private String name;
    private String line1;
    private String line2;
    private String line3;
    private String phone;
    private String email;
    private String city;
    @NotBlank(message = "state is required for shipping address")
    private String state;
    private String zip;
    private String country;
}