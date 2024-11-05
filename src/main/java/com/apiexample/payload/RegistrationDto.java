package com.apiexample.payload;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class RegistrationDto {

    private Long id;


    private String name;


    private String email;

    private String mobile;

    private String message;


}