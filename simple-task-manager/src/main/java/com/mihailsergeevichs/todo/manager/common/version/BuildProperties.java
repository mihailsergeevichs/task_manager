package com.mihailsergeevichs.todo.manager.common.version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BuildProperties {

    private final String time;
    private final String userEmail;
    private final String userName;

    @Autowired
    public BuildProperties(@Value("${git.build.time}") String time, @Value("${git.build.user.email}") String userEmail,
                           @Value("${git.build.user.name}") String userName){

        this.time = time;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "BuildProperties{" +
                "time='" + time + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserName() {
        return userName;
    }
}
