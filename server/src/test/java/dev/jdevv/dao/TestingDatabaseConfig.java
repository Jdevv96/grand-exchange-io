package dev.jdevv.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Objects;

/**
 * TestingDatabaseConfig configures the database-related beans and settings for testing.
 *
 * It sets the default values for environment variables and lastly performs any cleanup by
 * dropping the database and closing the connection.
 */

@Configuration
public class TestingDatabaseConfig {

    private static final String DB_HOST =
            Objects.requireNonNullElse(System.getenv("DB_HOST"), "localhost");
    private static final String DB_PORT =
            Objects.requireNonNullElse(System.getenv("DB_PORT"), "5432");
    private static final String DB_NAME =
            Objects.requireNonNullElse(System.getenv("DB_NAME"), "m2_final_project_test");
    private static final String DB_USER =
            Objects.requireNonNullElse(System.getenv("DB_USER"), "postgres");
    private static final String DB_PASSWORD =
            Objects.requireNonNullElse(System.getenv("DB_PASSWORD"), "password");

    private SingleConnectionDataSource adminDataSource;
    private JdbcTemplate adminJdbcTemp;

    @PostConstruct
    public void setup() {
        if (System.getenv("DB_HOST") == null) {
            adminDataSource = new SingleConnectionDataSource();
            adminDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            adminDataSource.setUsername("postgres");
            adminDataSource.setPassword("password");

            adminJdbcTemp = new JdbcTemplate(adminDataSource);
            adminJdbcTemp.update("DROP DATABASE IF EXISTS \"" + DB_NAME + "\";");
            adminJdbcTemp.update("CREATE DATABASE \"" + DB_NAME + "\";");
        }
    }

    private DataSource ds = null;

    @Bean
    public DataSource dataSource() throws SQLException {
        if (ds != null) return ds;

        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%s/%s", DB_HOST, DB_PORT, DB_NAME));
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setAutoCommit(false);

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-schema.sql"));
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("test-data.sql"));

        ds = dataSource;
        return ds;
    }

    @PreDestroy
    public void cleanup() throws SQLException {
        if (adminDataSource != null) {
            adminJdbcTemp.update("DROP DATABASE \"" + DB_NAME + "\";");
            adminDataSource.getConnection().close();
            adminDataSource.destroy();
        }
    }
}
