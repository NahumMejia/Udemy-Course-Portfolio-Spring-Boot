package com.portfolio.demo.Service;

import com.portfolio.demo.Exception.ValidationException;
import com.portfolio.demo.Model.PersonalInformation;
import com.portfolio.demo.Repository.IPersonalInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import org.springframework.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInformationServiceImplement implements IPersonalInformationService {
    private final Validator validator;
    private final IPersonalInformationRepository personalInformationRepository;

    @Override
    @Transactional
    public PersonalInformation save(PersonalInformation personalInformation) {
        BindingResult result = new BeanPropertyBindingResult(personalInformation, "personalInfo");
        validator.validate(personalInformation, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        return personalInformationRepository.save(personalInformation);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInformation> findById(Long id) {
        return personalInformationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInformation> findAll() {
        return personalInformationRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personalInformationRepository.deleteById(id);
    }
}
