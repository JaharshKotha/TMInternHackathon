package vote4tix.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vote4tix.api.GroupRequest;
import vote4tix.db.DatabaseManager;
import vote4tix.models.Invited;

import javax.sql.DataSource;


@RestController
@Configurable
public class AddGroupController {

    DataSource dataSource = DataSourceBuilder.create()
        .driverClassName("com.mysql.jdbc.Driver")
        .url("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3182766")
        .username("sql3182766")
        .password("6GWVYhlQpV")
        .build();

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource, false);

    DatabaseManager dbManager = new DatabaseManager(jdbcTemplate);

    @PostMapping("/addGroup")
    @ResponseStatus(HttpStatus.OK)
    public void addGroup(@RequestBody GroupRequest groupRequest) {
        System.out.println(groupRequest);
        for ( Invited person : groupRequest.users )
        {
            System.out.println(person);
        }
        dbManager.createGroup(groupRequest);
        truncateTables();
    }

    private void truncateTables() {
        jdbcTemplate.execute("TRUNCATE Users");
        jdbcTemplate.execute("TRUNCATE groups");
        jdbcTemplate.execute("TRUNCATE invites");
    }

}
