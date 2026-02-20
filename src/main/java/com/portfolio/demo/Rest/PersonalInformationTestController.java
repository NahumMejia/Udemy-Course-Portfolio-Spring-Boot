package com.portfolio.demo.Rest;

import com.portfolio.demo.Model.PersonalInformation;
import com.portfolio.demo.Service.IPersonalInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-information")
@RequiredArgsConstructor
public class PersonalInformationTestController {

    private final IPersonalInformationService personalInformationService;

    @GetMapping("/all")
    public List<PersonalInformation> getAllPersonalInformation(){
        return personalInformationService.findAll();
    }

    @GetMapping("{id}")
    public PersonalInformation getPersonalInformationById(@PathVariable Long id){
        Optional<PersonalInformation> information = personalInformationService.findById(id);
        if(information.isPresent()){
            return information.get();
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Personal information not found");
        }
    }
    @PostMapping("/new")
    public ResponseEntity<PersonalInformation> createPersonalInformation(@RequestBody PersonalInformation personalInformation){
        PersonalInformation newPersonalInformation = personalInformationService.save(personalInformation);
        return new ResponseEntity<>(newPersonalInformation, HttpStatus.CREATED);
    }
}
