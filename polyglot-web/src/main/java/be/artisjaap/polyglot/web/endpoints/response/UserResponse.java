package be.artisjaap.polyglot.web.endpoints.response;

import be.artisjaap.polyglot.core.action.to.PracticeWordTO;
import be.artisjaap.polyglot.core.action.to.UserTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static List<UserResponse> from(List<UserTO> users){
        return users.stream().map(UserResponse::from).collect(Collectors.toList());
    }


    public static UserResponse from(UserTO userTO){
        UserResponse userResponse = new UserResponse();
        userResponse.username = userTO.username();
        userResponse.firstName = userTO.firstName();
        userResponse.lastName = userTO.lastName();

        return userResponse;
    }
}
