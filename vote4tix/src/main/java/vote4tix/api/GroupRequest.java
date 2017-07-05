package vote4tix.api;

import vote4tix.models.Invited;

public class GroupRequest {
    public Invited[] users;
    public String eventId;
    public String message;
}