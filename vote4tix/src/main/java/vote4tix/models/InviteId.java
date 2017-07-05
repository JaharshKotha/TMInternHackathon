package vote4tix.models;

public class InviteId {
    User user;
    Event eventId;
    Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public InviteId(User user, Event event, Group group) {
        this.user = user;
        this.eventId = eventId;
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }
}
