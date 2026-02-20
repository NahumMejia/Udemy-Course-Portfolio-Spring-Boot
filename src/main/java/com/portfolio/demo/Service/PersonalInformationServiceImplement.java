package com.portfolio.demo.Service;

import com.portfolio.demo.Model.PersonalInformation;
import com.portfolio.demo.Repository.IPersonalInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalInformationServiceImplement implements IPersonalInformationService {

    private final IPersonalInformationRepository personalInformationRepository;

    @Override
    public PersonalInformation save(PersonalInformation personalInformation) {
        return personalInformationRepository.save(personalInformation);
    }

    @Override
    public Optional<PersonalInformation> findById(Long id) {
        return personalInformationRepository.findById(id);
    }

    @Override
    public List<PersonalInformation> findAll() {
        return personalInformationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInformationRepository.deleteById(id);
    }
}
