package be.artisjaap.polyglot.web.endpoints;

import be.artisjaap.polyglot.core.action.RegisterUser;
import be.artisjaap.polyglot.core.action.to.NewUserTO;
import be.artisjaap.polyglot.core.action.to.UserTO;
import be.artisjaap.polyglot.web.action.AuthenticateForUser;
import be.artisjaap.polyglot.web.endpoints.request.NewUserRequest;
import be.artisjaap.polyglot.web.endpoints.response.UserLoginResponse;
import be.artisjaap.polyglot.web.security.JwtTokenUtil;
import be.artisjaap.polyglot.web.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class LoginController {
    @Autowired
    private AuthenticateForUser authenticateForUser;

    @Autowired
    private RegisterUser registerUser;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Registered user can login with this URL
     * */
    @RequestMapping(value = "/api/login/{username}/{password}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<UserLoginResponse> login(@PathVariable String username, @PathVariable String password){
        UserTO userTO = authenticateForUser.byUsernameAndPassword(username, password)
                .orElseThrow(() -> new InsufficientAuthenticationException("invalid username password"));
        JwtUser jwtUser = createJwtUser(userTO);
        final String token = jwtTokenUtil.generateToken(jwtUser);

        return ResponseEntity.ok(UserLoginResponse.from(userTO, token));

    }

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UserLoginResponse> login(@RequestBody NewUserRequest newUserRequest){
        UserTO userTO = registerUser.newUser(NewUserTO.newBuilder().withUsername(newUserRequest.getUsername())
                .withPassword(newUserRequest.getPassword())
                .build());
        JwtUser jwtUser = createJwtUser(userTO);
        final String token = jwtTokenUtil.generateToken(jwtUser);
        return ResponseEntity.ok(UserLoginResponse.from(userTO, token));

    }


    private JwtUser createJwtUser(UserTO userTO) {
        return JwtUser.newBuilder().withUserId(userTO.id())
                .withUsername(userTO.username())
                .withPassword(userTO.password())
                .build();
    }
}
