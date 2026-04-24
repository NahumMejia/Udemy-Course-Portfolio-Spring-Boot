package com.portfolio.demo.Service;

import com.portfolio.demo.Model.PersonalInformation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IPersonalInformationService {
    @Transactional
    PersonalInformation save(PersonalInformation personalInformation);

    Optional<PersonalInformation> findById(Long id);

    List<PersonalInformation> findAll();

    void deleteById(Long id);
}
