package br.com.orangetalents.proposta.apihealth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component("Database")
public class DataBaseHealthIndicator implements HealthIndicator, HealthContributor {

    @Autowired
    private DataSource dataSource;

    @Override
    public Health health() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT documento FROM proposta");
            return Health.status(Status.UP).build();
        } catch (SQLException throwables) {
            return Health.status(Status.DOWN).withException(throwables).build();
        }
    }

}
