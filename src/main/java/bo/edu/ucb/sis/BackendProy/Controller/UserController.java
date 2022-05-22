package bo.edu.ucb.sis.BackendProy.Controller;

import bo.edu.ucb.sis.BackendProy.entity.User;
import bo.edu.ucb.sis.BackendProy.model.JwtUser;
import bo.edu.ucb.sis.BackendProy.security.JwtGenerator;
import bo.edu.ucb.sis.BackendProy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtGenerator jwtGenerator;
    @PostMapping(value = "/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
        if(userService.findUser(user)==null){
            userService.save(user);
            User userDb = userService.checkUserLogin(user);
            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserid(userDb.getUser_id());
            jwtUser.setUserName(userDb.getEmail());

            return new ResponseEntity<>((Collections.singletonMap("jwtToken", jwtGenerator.generate(jwtUser))),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        User userDb = userService.checkUserLogin(user);
        if(userDb!=null){
            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserid(userDb.getUser_id());
            jwtUser.setUserName(userDb.getEmail());

            return new ResponseEntity<>((Collections.singletonMap("jwtToken", jwtGenerator.generate(jwtUser))),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
