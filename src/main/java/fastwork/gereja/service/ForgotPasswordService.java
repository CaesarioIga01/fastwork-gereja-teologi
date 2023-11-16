package fastwork.gereja.service;

import fastwork.gereja.entity.User;
import fastwork.gereja.repository.ForgotPasswordRepository;
import fastwork.gereja.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    public String forgotPass(String username) {

        Optional<User> optional = Optional.ofNullable(forgotPasswordRepository.findByUsername(username));

        if(optional.isEmpty()){
            return "Invalid email id.";
        }

        User user=optional.get();
        user.setToken(generateToken());

        user=forgotPasswordRepository.save(user);
        return user.getToken();
    }

    public String resetPass(String token, String password){
        Optional<User> userOptional= Optional.ofNullable(forgotPasswordRepository.findByToken(token));

        if(!userOptional.isPresent()){
            return "Invalid token";
        }
        User user = userOptional.get();

        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        forgotPasswordRepository.save(user);

        return "Your password successfully updated.";
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();

        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }
}
