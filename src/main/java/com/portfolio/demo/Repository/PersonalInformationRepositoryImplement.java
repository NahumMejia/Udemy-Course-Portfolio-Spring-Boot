package com.portfolio.demo.Repository;

import com.portfolio.demo.Model.PersonalInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInformationRepositoryImplement implements IPersonalInformationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInformation> personalInformationRowMapper = (resultSet, numberRow) -> {
        PersonalInformation information = new PersonalInformation();
        information.setId(resultSet.getLong("id"));
        information.setFirstName(resultSet.getString("first_name"));
        information.setLastName(resultSet.getString("last_name"));
        information.setTitle(resultSet.getString("title"));
        information.setProfileDescription(resultSet.getString("profile_description"));
        information.setProfileImageUrl(resultSet.getString("profile_image_url"));
        information.setYearsOfExperience(resultSet.getObject("years_of_experience", Integer.class));
        information.setEmail(resultSet.getString("email"));
        information.setPhone(resultSet.getString("phone"));
        information.setLinkedinURL(resultSet.getString("linkedin_url"));
        information.setGithubURL(resultSet.getString("github_url"));
        return information;
    };

    @Override
    public PersonalInformation save(PersonalInformation personalInformation) {
        if(personalInformation.getId()==null){
            String sql = "INSERT INTO personal_info (first_name,last_name," +
                    "title,profile_description,profile_image_url," +
                    "years_of_experience,email,phone,linkedin_url,github_url) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

            jdbcTemplate.update(sql,
                    personalInformation.getFirstName(),
                    personalInformation.getLastName(),
                    personalInformation.getTitle(),
                    personalInformation.getProfileDescription(),
                    personalInformation.getProfileImageUrl(),
                    personalInformation.getYearsOfExperience(),
                    personalInformation.getEmail(),
                    personalInformation.getPhone(),
                    personalInformation.getLinkedinURL(),
                    personalInformation.getGithubURL()
            );
        }
        else{
            String sql = "UPDATE personal_info SET first_name=?, last_name=?, title=?, profile_description=?, profile_image_url=?, years_of_experience=?, email=?, phone=?, linkedin_url=?, github_url=? WHERE id=?";

            jdbcTemplate.update(sql,
                    personalInformation.getFirstName(),
                    personalInformation.getLastName(),
                    personalInformation.getTitle(),
                    personalInformation.getProfileDescription(),
                    personalInformation.getProfileImageUrl(),
                    personalInformation.getYearsOfExperience(),
                    personalInformation.getEmail(),
                    personalInformation.getPhone(),
                    personalInformation.getLinkedinURL(),
                    personalInformation.getGithubURL(),
                    personalInformation.getId()
            );
        }
        return personalInformation;
    }

//    @Override
//    public Optional<PersonalInformation> findById(Long id) {
//        String sql = "Select * From personal_info Where id = ?";
//        List<PersonalInformation> infos = jdbcTemplate.query(sql, personalInformationRowMapper);
//        return infos.stream().findFirst();
//    }

    @Override
    public Optional<PersonalInformation> findById(Long id) {
        String sql = "SELECT * FROM personal_info WHERE id=?";
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, personalInformationRowMapper, id));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    @Override
    public List<PersonalInformation> findAll() {
        String sql = "SELECT * FROM personal_info";
        return jdbcTemplate.query(sql,personalInformationRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM personal_info WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
