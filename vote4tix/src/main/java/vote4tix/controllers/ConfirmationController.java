package vote4tix.controllers;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vote4tix.models.Event;
import vote4tix.models.Group;
import vote4tix.models.InviteId;
import vote4tix.models.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class ConfirmationController {
    @RequestMapping("/inviteLanding/{inviteId}")
    public String inviteLanding(@PathVariable String inviteId) {
        File confirmationPageHtmlFile = null;
        try {
            confirmationPageHtmlFile = ResourceUtils.getFile("classpath:assets/html/confirmPage.html");
        } catch (FileNotFoundException e) {
            return "Down for the count " + e.getMessage();
        }

        return formatEventTemplate(getInviteId(inviteId), confirmationPageHtmlFile);
    }

    /*
    String eventId;
    String artist;
    String eventImage;
    */
    public InviteId getInviteId(String inviteId) {
        User user = new User("John", "Something", "John@something.com");
        Event event = new Event("1234", "U2",
            "https://s1.ticketm.net/dam/a/1c0/fdbb278d-8de9-4250-b8da-66af4d02c1c0_264671_TABLET_LANDSCAPE_3_2.jpg",
            "$300");
        ArrayList<User> users = new ArrayList<User>();
        users.add(user);
        Group group = new Group(1, event, users);
        return new InviteId(user, event, group);
    }

    /*
        Username,
        event name,
        event cost,
        event artist
        event image
     */
    public String formatEventTemplate(InviteId inviteId, File confirmationPageHtmlFile) {
        User user = inviteId.getUser();
        Event event = inviteId.getEventId();
        Group group = inviteId.getGroup();
        List<User> confirmedUsers = group.getConfirmedUsers();

        String confirmationPageHtml = null;
        try {
            confirmationPageHtml = new Scanner(confirmationPageHtmlFile).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            return "Down for the count " + e.getMessage();
        }

        return String.format(confirmationPageHtml,
            user.getFirstName() + " " + user.getLastName(),
            event.getArtist(),
            event.getEventCost(),
            buildConfirmedUsersHtml(confirmedUsers),
            event.getArtist(),
            event.getEventImage()
        );
    }

    private String buildConfirmedUsersHtml(List<User> confirmedUsers) {
        String usersHtml = "";
        for (User user : confirmedUsers) {
            usersHtml = usersHtml + "<li>" + user.getFirstName() + " " + user.getLastName() + "</li>";
        }
        return usersHtml;
    }
}