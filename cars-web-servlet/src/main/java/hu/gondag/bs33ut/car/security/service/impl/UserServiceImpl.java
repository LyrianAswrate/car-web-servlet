package hu.gondag.bs33ut.car.security.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.gondag.bs33ut.car.dto.UserDTO;
import hu.gondag.bs33ut.car.exceptions.UserServiceException;
import hu.gondag.bs33ut.car.security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Qualifier("sqliteConncetion")
    @Autowired
    private Connection sqliteConncetion;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDTO getUserByUsername(String username) {
        UserDTO user = null;
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ").append(UserService.TABLE_NAME).append(" WHERE ").append(UserService.COLUMN_USERNAME).append(" =\"")
                    .append(username).append("\"");
            ResultSet resultSet = statement.executeQuery(sb.toString());

            while (resultSet.next()) {
                user = new UserDTO();
                user.setId(resultSet.getLong(UserService.COLUMN_ID));
                user.setUsername(resultSet.getString(UserService.COLUMN_USERNAME));
                user.setPassword(resultSet.getString(UserService.COLUMN_PASSWORD));
                user.setFullname(resultSet.getString(UserService.COLUMN_FULLNAME));
            }
        } catch (Exception e) {
            UserServiceImpl.LOG.error(e.getMessage(), e);
            user = null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    UserServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
        return user;
    }

    @Override
    public void createUser(String username, String fullname, String password) {
        Statement statement = null;
        try {
            statement = sqliteConncetion.createStatement();
            StringBuilder sb = new StringBuilder();
            sb.append("insert into ").append(UserService.TABLE_NAME).append(" (");
            sb.append(UserService.COLUMN_USERNAME).append(",");
            sb.append(UserService.COLUMN_PASSWORD).append(",");
            sb.append(UserService.COLUMN_FULLNAME);
            sb.append(") values(");
            sb.append("\"").append(username).append("\"").append(",");
            sb.append("\"").append(encoder.encode(password)).append("\"").append(",");
            sb.append("\"").append(fullname).append("\"").append(");");
            statement.executeUpdate(sb.toString());
        } catch (Exception e) {
            UserServiceImpl.LOG.error(e.getMessage(), e);
            throw new UserServiceException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    UserServiceImpl.LOG.error(e.getMessage(), e);
                }
            }
        }
    }

}
