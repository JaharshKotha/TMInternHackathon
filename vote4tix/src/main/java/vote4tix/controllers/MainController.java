package vote4tix.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MainController {
    @RequestMapping(method= RequestMethod.GET)
    public String getInviteDetails() {
        return "";
    }
}
