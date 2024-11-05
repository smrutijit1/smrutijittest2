package com.apiexample.service;

import com.apiexample.entity.Registration;
import com.apiexample.payload.RegistrationDto;
import com.apiexample.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class RegistrationServiceImpl  implements  RegistrationService {
    private RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

//create
    @Override
    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);
        RegistrationDto dto = mapToDto(savedEntity);
        dto.setMessage("Registration saved");// comment after response
        return dto;


    }
//map to entity
    Registration mapToEntity(RegistrationDto dto) {
        Registration entity = new Registration();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());

        return entity;
    }
//map to dto
    RegistrationDto mapToDto(Registration registration) {
        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(dto.getName());
        dto.setEmail(dto.getEmail());
        dto.setMobile(dto.getMobile());

        return dto;


    }
//delete
    @Override
    public void deleteRegistrationById(long id) {
        registrationRepository.deleteById(id);


    }
//update
    @Override
    public RegistrationDto updateRegistration(long id, RegistrationDto registrationDto) {
        Optional<Registration> opReg = registrationRepository.findById(id);
        if (opReg.isPresent()) {
            Registration registration = opReg.get();
            registration.setName(registrationDto.getName());
            registration.setEmail(registrationDto.getEmail());
            registration.setMobile(registrationDto.getMobile());
            Registration savedEntity = registrationRepository.save(registration);
            return mapToDto(savedEntity);
        } else {
            throw new RuntimeException("Registration not found with id: " + id);
        }
    }
//retrive
    @Override
    public List<RegistrationDto> getAllRegistrations() {
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> registrationDtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return registrationDtos;
    }

}
