package fpt.mooc.controller;

import fpt.mooc.dto.companyDto;
import fpt.mooc.dto.userDto;
import fpt.mooc.entities.ServiceResult;
import fpt.mooc.service.CompanyService;
import fpt.mooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping(value = "/company/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResult<Boolean> addUser(@RequestBody companyDto companyDto) {
        return companyService.insert(companyDto);
    }
    @PostMapping(value = "/company/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResult<Boolean> updateUser(@RequestBody companyDto companyDto, @PathVariable("id") Integer id) {
        return companyService.update(companyDto,id);
    }
}
