package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.user.ListUsers;
import be.artisjaap.polyglot.web.endpoints.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private ListUsers listUsers;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<UserResponse>> allUsers() {
        ;

        return ResponseEntity.ok(UserResponse.from(listUsers.allUsers()));
    }
}
