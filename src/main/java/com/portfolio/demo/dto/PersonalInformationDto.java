package com.portfolio.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class PersonalInformationDto {

    private Long id;
    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;
    private String lastName;
    private String title;
    private String profileDescription;
    private String profileImageUrl;
    private Integer yearsOfExperience;
    private String email;
    private String phone;
    private String linkedinURL;
    private String githubURL;
}
