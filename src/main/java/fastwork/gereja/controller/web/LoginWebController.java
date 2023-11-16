package fastwork.gereja.controller.web;

import fastwork.gereja.entity.Login;
import fastwork.gereja.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginWebController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new Login());
        return mav;
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user ) {

        Login oauthUser = loginService.login(user.getUsername(), user.getPassword());

        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser))
        {

            return "redirect:/kidung-lagu";

        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpServletResponse response)
    {


        return "redirect:/";
    }
}