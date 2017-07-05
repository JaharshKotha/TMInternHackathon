package vote4tix.db;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import vote4tix.api.GroupRequest;
import vote4tix.models.Event;
import vote4tix.models.Group;
import vote4tix.models.InviteId;
import vote4tix.models.Invited;
import vote4tix.models.User;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {

    JdbcTemplate jdbcTemplate;

    public DatabaseManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createGroup(GroupRequest groupRequest) {
        String groupCreateSQL = String.format("INSERT INTO groups (eventId) values ('%s') on duplicate key update eventId=eventId",
            groupRequest
            .eventId);
        jdbcTemplate.execute(groupCreateSQL);

        Map<String, Object> qMap = jdbcTemplate.queryForMap("SELECT DISTINCT * FROM groups WHERE eventId=" + groupRequest.eventId);
        System.out.println(qMap);

        Integer groupId = (Integer) qMap.get("groupId");


        for (Invited invited : groupRequest.users) {
            Integer userId = createUser(invited);
            Integer invitedId = createInvitation(invited, userId, groupId);
            sendText(invitedId, invited.email, invited.mobile,invited.message);
        }


    }

    public String sendText(Integer inviteId, String email, String mobile,String message) {
        final String ACCOUNT_SID = "";
        final String AUTH_TOKEN = "";

        final String fromPhone = "+1";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
            .creator(new PhoneNumber(mobile),new PhoneNumber(fromPhone),
                String.format("Hey, %s! %s! http://localhost:8080/inviteLanding/%s",
                    email, message ,inviteId))
            .create();
        return "sms_good";
    }

    private Integer createInvitation(Invited invited, Integer userId, Integer groupId) {
        String createUserSQL = "INSERT INTO invites (userId, groupId) VALUES ('%s', '%s') on duplicate key update userId=userId";
        jdbcTemplate.update(String.format(createUserSQL, userId, groupId));
        System.out.println(userId);
        System.out.println(groupId);
        return (Integer) jdbcTemplate.queryForMap(String.format("SELECT DISTINCT * FROM invites WHERE userId='%s' AND groupId='%s'", userId,
            groupId)).get("inviteId");
    }

    private Integer createUser(Invited invited) {
        String createUserSQL = "INSERT INTO Users (email, mobile) VALUES ('%s', '%s') on duplicate key update email=email";
        jdbcTemplate.update(String.format(createUserSQL, invited.email, invited.mobile));

        return (Integer) jdbcTemplate.queryForMap(String.format("SELECT DISTINCT * FROM Users WHERE email='%s' AND mobile='%s'", invited
            .email,
            invited.mobile)).get("userId");
    }

    public InviteId getInviteInformation(Integer inviteId) {
        /*
        Map<String, Object> invitesTable = jdbcTemplate.queryForMap(
            String.format("SELECT * FROM invites WHERE inviteId=''%s''", inviteId.toString()));
        String userId = (String) invitesTable.get("userId");
        Map<String, Object> UsersTable = jdbcTemplate.queryForMap(String.format("SELECT * FROM Users where userId='%s'", userId));
        String userName = (String) UsersTable.get("email");
        Map<String, Object> groupsTable = jdbcTemplate.queryForMap(String.format("SELECT * FROM groups WHERE inviteId='%s'", inviteId
            .toString()));

        Group group = new Group();

        return new InviteId(userName, eventId, group);*/
        return null;
    }
}
