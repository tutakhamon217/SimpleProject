package fpt.mooc.service;

import fpt.mooc.dto.companyDto;
import fpt.mooc.entities.Authority;
import fpt.mooc.entities.Company;
import fpt.mooc.entities.ServiceResult;
import fpt.mooc.entities.User;
import fpt.mooc.repository.CompanyRepository;
import fpt.mooc.repository.UserRepository;
import fpt.mooc.utility.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public ServiceResult<Boolean> insert(companyDto companyDto) {

        if (companyRepository.findByName(companyDto.getName()) != null) {
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "company's name already exist", true);
        }

        try {
            Company company = new Company(companyDto.getName(),companyDto.getAddress(),companyDto.getEquity(),companyDto.getDebt());
            Company companyAdded = companyRepository.save(company);
            if (null == companyAdded) {
                return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Add company fail", true);
            } else {
                return new ServiceResult<>(HttpStatus.OK, "Add company success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Add company fail", true);
        }
    }

    @Override
    public ServiceResult<Boolean> update(companyDto companyDto, Integer id) {
        if (companyRepository.findById(id) == null) {
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "company is not exist", true);
        }
        try {
            Company old =companyRepository.findById(id).get();
            old.setName(companyDto.getName());
            old.setAddress(companyDto.getAddress());
            old.setEquity(companyDto.getEquity());
            old.setDebt(companyDto.getDebt());

            Company companyUpdated = companyRepository.save(old);
            if (null == companyUpdated) {
                return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Update Company fail", true);
            } else {
                return new ServiceResult<>(HttpStatus.OK, "Update Company success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Update Company fail", true);
        }
    }
}
