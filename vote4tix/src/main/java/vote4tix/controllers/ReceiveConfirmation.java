package vote4tix.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vote4tix.Vote4TixJDBCController;

@RestController
@RequestMapping("/receiveConfirmation")
public class ReceiveConfirmation {    
    @RequestMapping(method= RequestMethod.GET)
    public String getInviteDetails() {
        Vote4TixJDBCController jdb = new Vote4TixJDBCController();
        jdb.insertUser();
        return "Received cofirmation";
    }
}