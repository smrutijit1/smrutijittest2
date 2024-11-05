package com.apiexample.controller;


import com.apiexample.entity.Registration;
import com.apiexample.payload.RegistrationDto;
import com.apiexample.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {


    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<RegistrationDto> addRegistration(@RequestBody RegistrationDto registrationDto) {
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);

    }


    // http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam long id) {
        registrationService.deleteRegistrationById(id);

      return new ResponseEntity<>("Registration deleted",HttpStatus.OK);

    }
       
    @PutMapping
    public ResponseEntity<RegistrationDto> updateRegistration(@RequestParam long id, @RequestBody RegistrationDto registrationDto) {
        RegistrationDto  dto = registrationService.updateRegistration(id, registrationDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


@GetMapping
    public ResponseEntity<List<RegistrationDto>>getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getAllRegistrations();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }






    } 









