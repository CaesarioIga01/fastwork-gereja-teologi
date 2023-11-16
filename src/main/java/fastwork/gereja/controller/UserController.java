package fastwork.gereja.controller;

import fastwork.gereja.entity.User;
import fastwork.gereja.model.*;
import fastwork.gereja.repository.UserRepository;
import fastwork.gereja.service.ForgotPasswordService;
import fastwork.gereja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;
    @Autowired
    private ForgotPasswordService service;

    @PostMapping(
            path = "/api/auth/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<RegisterResponse> register(@RequestBody RegisterUserRequest request) {
        RegisterResponse register = userService.register(request);
        return WebResponse.<RegisterResponse>builder().data(register).build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> get(User user) {
        UserResponse userResponse = userService.get(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PostMapping("/forgot-password")
    public Map<String, String> forgotPass(@RequestParam String username){

        Map<String, String> response = new HashMap<>();
        response.put("token",service.forgotPass(username));

        return response;
    }

    @PutMapping("/reset-password")
    public Map<String, String> resetPass(@RequestParam String token, @RequestParam String password){

        Map<String, String> response = new HashMap<>();
        response.put("Message", service.resetPass(token, password));

        return response;
    }
}
