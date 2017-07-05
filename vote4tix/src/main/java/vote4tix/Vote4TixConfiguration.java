package vote4tix;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Vote4TixConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(Vote4TixConfiguration.class, args);
    }
/*
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        DataSource dataSource = DataSourceBuilder.create()
            .driverClassName("com.mysql.jdbc.Driver")
            .url("jdbc:mysql://sql3.freemysqlhosting.net:3306/sql3182766")
            .username("sql3182766")
            .password("6GWVYhlQpV")
            .build();
        return new JdbcTemplate(dataSource, false);
    }
*/
}
