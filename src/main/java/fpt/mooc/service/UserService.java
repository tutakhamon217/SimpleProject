package fpt.mooc.service;

import fpt.mooc.dto.userDto;
import fpt.mooc.entities.ServiceResult;
import fpt.mooc.entities.User;

import java.time.LocalDate;
import java.util.Set;

public interface UserService {
    ServiceResult<Boolean> insert(userDto userDto);
    ServiceResult<Boolean> update(userDto userDto,Integer id);

    ServiceResult<Boolean> delete(Integer id);
//
//    boolean updateUserForTeacher(String login, String fullName, String phone, String ImageUrl, String email, Set authorities,String username);
//
//    User insertUserForStudent(String login, String fullName, String phone, String ImageUrl, String email, Set authorities,String username);


}
