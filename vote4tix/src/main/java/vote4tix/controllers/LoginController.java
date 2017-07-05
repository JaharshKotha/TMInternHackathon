package vote4tix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vote4tix.models.User;

@RestController
@RequestMapping("/login")
public class LoginController {    
    @RequestMapping(method= RequestMethod.POST)
    public String getInviteDetails(@RequestBody User value1) {
        return "logged in successfully";
    }
}
