package com.thoughtworks.capacity.gtb.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping
@Validated
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    // GET /users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return registerService.getAllUsers();
    }

    // POST /register
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid User user) {
        if(registerService.checkExistUsername(user.getUsername())){
            throw new UserAlreadyExistException("用户名已存在");
        }
        registerService.createUser(user);
    }

    @GetMapping("/login/{username}/{password}")
    public User UserLoginRestful(@PathVariable("username") @Size(min = 5, max = 12, message = "用户名不合法") String username, @PathVariable("password") String password) {
        User user = registerService.getUser(username, password);
        if(user == null){
            throw new UserAlreadyExistException("用户名或密码错误");
        }
        return user;
    }
}
