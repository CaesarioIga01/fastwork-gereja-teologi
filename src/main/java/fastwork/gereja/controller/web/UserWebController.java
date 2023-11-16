package fastwork.gereja.controller.web;

import fastwork.gereja.entity.User;
import fastwork.gereja.repository.UserRepository;
import fastwork.gereja.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserWebController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;

    @GetMapping("/user")
    public String viewUser(Model model) {

        List<User> listUser = userService.listUser();
        model.addAttribute("listUser", listUser);
        return "user";
    }
}
