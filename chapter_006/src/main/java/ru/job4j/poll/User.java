package ru.job4j.poll;

/**
 * Class User.
 *
 * @author dshustrov
 * @version 1
 * @since 28.01.2019
 */
public class User {
    /**
     * userName name of user.
     */
    private String userName;
    /**
     * email of user.
     */
    private String email;

    /**
     * Constructor default.
     */
    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
