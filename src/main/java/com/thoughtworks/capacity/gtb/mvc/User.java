package com.thoughtworks.capacity.gtb.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;

    @Size(min = 5, max = 12, message = "用户名不合法")
    private String username;

    @Size(min = 5, max = 12, message = "密码不合法")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "密码不合法")
    @Pattern(regexp = ".*[\\d].*", message = "密码不合法")
    @Pattern(regexp = ".*[_].*", message = "密码不合法")
    private String password;

    @Email(message = "邮箱不合法")
    private String email;

    public User(int id, String bob, String bob123, String s) {
        this.id = id;
        this.username = bob;
        this.password = bob123;
        this.email = s;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return this.id;
    }

    public String getUsername() {
        return username;
    }
}
