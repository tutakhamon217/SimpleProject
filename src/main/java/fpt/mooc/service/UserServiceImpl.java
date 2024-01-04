package fpt.mooc.service;

import fpt.mooc.dto.userDto;
import fpt.mooc.entities.Authority;
import fpt.mooc.entities.ServiceResult;
import fpt.mooc.entities.User;
import fpt.mooc.repository.UserRepository;

import fpt.mooc.utility.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    ConvertStringToAuthority convert;


    @Override
    public ServiceResult<Boolean> insert(userDto userDto) {
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (userRepository.findByUserName(userDto.getUserName()) != null) {
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "employee's user name already exist", true);
        }
        Set<Authority> roles = convert.convertStringToAuthority(userDto.getAuthorities());
        try {
            User user = new User(userDto.getUserName(), encoder.encode(Password.PassWord), userDto.getFullName(),
                    userDto.getAddress(), userDto.getPhone(), userDto.getSex(), LocalDate.parse(userDto.getBirthDay(), ft), roles);
            User userAdded = userRepository.save(user);
            if (null == userAdded) {
                return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Add employee fail", true);
            } else {
                return new ServiceResult<>(HttpStatus.OK, "Add employee success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Add employee fail", true);
        }
    }

    @Override
    public ServiceResult<Boolean> update(userDto userDto, Integer id) {
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (userRepository.findById(id) == null) {
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Employee doesn't exist", true);
        }
        Set<Authority> roles = convert.convertStringToAuthority(userDto.getAuthorities());
        try {
            User old =userRepository.findById(id).get();
            old.setUserName(userDto.getUserName());
            old.setFullName(userDto.getFullName());
            old.setAddress(userDto.getAddress());
            old.setSex(userDto.getSex());
            old.setBirthDay(LocalDate.parse(userDto.getBirthDay(), ft));
            old.setAuthorities(roles);
            User userAdded = userRepository.save(old);
            if (null == userAdded) {
                return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Update employee fail", true);
            } else {
                return new ServiceResult<>(HttpStatus.OK, "Update employee success", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Update employee fail", true);
        }
    }

    @Override
    public ServiceResult<Boolean> delete(Integer id) {
        if (userRepository.findById(id) == null) {
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Employee doesn't exist", true);
        }
        try {
            userRepository.deleteById(id);

            return new ServiceResult<>(HttpStatus.OK, "Delete employee success", true);

        } catch (Exception e) {
            e.printStackTrace();
            return new ServiceResult<>(HttpStatus.BAD_REQUEST, "Update employee fail", true);
        }
    }

}
