package fpt.mooc.controller;

import fpt.mooc.dto.userDto;
import fpt.mooc.entities.ServiceResult;
import fpt.mooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/add", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResult<Boolean> addUser(@RequestBody userDto userDto) {
        return userService.insert(userDto);
    }
    @PostMapping(value = "/user/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResult<Boolean> updateUser(@RequestBody userDto userDto, @PathVariable("id") Integer id) {
        return userService.update(userDto,id);
    }
    @DeleteMapping(value = "/user/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ServiceResult<Boolean> deleteUser(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }

}
