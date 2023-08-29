package dev.jdevv.server;

import dev.jdevv.dao.JdbcUserDao;
import dev.jdevv.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserDaoTests extends BaseDaoTests {
    protected static final User USER_1 = new User(1, "user1","user1","ROLE_USER", "John Doe", "555 User1 Way", "Cincinnati", "OH", "00003");
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER", "Jane Doe", null, "Cleveland", "OH", "00002");
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER", "Junior Doe", "555 User3 Way", "Columbus", "OH", "00001");

    private JdbcUserDao userDao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        userDao = new JdbcUserDao(jdbcTemplate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findIdByUsername_given_null_throws_exception() {
        userDao.findIdByUsername(null);
    }
}
