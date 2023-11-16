package fastwork.gereja.service;

import fastwork.gereja.entity.User;
import fastwork.gereja.model.RegisterResponse;
import fastwork.gereja.model.RegisterUserRequest;
import fastwork.gereja.model.UpdateUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import fastwork.gereja.model.UserResponse;
import fastwork.gereja.repository.UserRepository;
import fastwork.gereja.security.BCrypt;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public RegisterResponse register(RegisterUserRequest request) {
        validationService.validate(request);

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());
        user.setSector(request.getSector());
        user.setNumberKK(request.getNumberKK());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);

        return RegisterResponse.builder()
                .id(request.getUsername())
                .name(request.getName())
                .username(request.getUsername())
                .numberKK(request.getNumberKK())
                .sector(request.getSector())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Transactional
    public UserResponse update(User user, UpdateUserRequest request) {
        validationService.validate(request);

        log.info("REQUEST : {}", request);

        if (Objects.nonNull(request.getName())) {
            user.setName(request.getName());
        }

        if (Objects.nonNull(request.getPassword())) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }

        userRepository.save(user);

        log.info("USER : {}", user.getName());

        return UserResponse.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }
}
