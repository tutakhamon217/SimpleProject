package fpt.mooc.service;

import fpt.mooc.dto.companyDto;
import fpt.mooc.dto.userDto;
import fpt.mooc.entities.ServiceResult;

public interface CompanyService {
    ServiceResult<Boolean> insert(companyDto companyDto);
    ServiceResult<Boolean> update(companyDto companyDto,Integer id);
}
