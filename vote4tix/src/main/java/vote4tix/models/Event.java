package vote4tix.models;

public class Event {

    String eventId;
    String artist;
    String eventImage;
    String eventCost;

    public String getEventCost() {
        return eventCost;
    }

    public void setEventCost(String eventCost) {
        this.eventCost = eventCost;
    }

    public Event(String eventId, String artist, String eventImage, String eventCost) {
        this.eventId = eventId;
        this.artist = artist;
        this.eventImage = eventImage;
        this.eventCost = eventCost;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
