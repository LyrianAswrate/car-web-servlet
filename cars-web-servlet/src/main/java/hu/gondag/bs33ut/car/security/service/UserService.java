package hu.gondag.bs33ut.car.security.service;

import hu.gondag.bs33ut.car.dto.UserDTO;

public interface UserService {

    public static final String TABLE_NAME = "USERS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_FULLNAME = "FULLNAME";

    public UserDTO getUserByUsername(String username);

    public void createUser(String username, String fullname, String password);

}
