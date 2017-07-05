package vote4tix.models;

import java.util.List;

public class Group {
    Integer id;
    Event event;
    List<User> confirmedUsers;

    public Group(Integer id, Event event, List<User> confirmedUsers) {
        this.id = id;
        this.event = event;
        this.confirmedUsers = confirmedUsers;
    }

    public Group(Invited[] invitationList) {
        for ( Invited invited : invitationList ) {
            createInvitation(invited);
        }
    }

    private void createInvitation(Invited invited) {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<User> getConfirmedUsers() {
        return confirmedUsers;
    }

    public void setConfirmedUsers(List<User> confirmedUsers) {
        this.confirmedUsers = confirmedUsers;
    }
}