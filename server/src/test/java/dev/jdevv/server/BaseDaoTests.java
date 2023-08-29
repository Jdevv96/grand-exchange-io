package dev.jdevv.server;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * BaseDaoTests is an abstract base class, used for testing our DAOs.
 * It specifies which test runner to use, followed by the configuration to be used.
 *
 * This class gives us access to the database connection via datasource, and it performs a rollback
 * after every test to ensure the database remains in a consistent state.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public abstract class BaseDaoTests {

    @Autowired
    protected DataSource dataSource;

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }
}
