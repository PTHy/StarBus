package com.starbus.starbus.Controller;

import com.starbus.starbus.Domain.User;
import com.starbus.starbus.Protocol.ResponseFormat;
import com.starbus.starbus.Protocol.ResponseType;
import com.starbus.starbus.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/api/users/account/register")
    public ResponseEntity register (@RequestBody @Valid User u, Errors errors)
    {
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                logger.warn(error.toString());
            }

            return ResponseEntity
                    .badRequest()
                    .body(new ResponseFormat(ResponseType.VALIDATE_ERROR, null));
        }

        try {
            HashMap<String, Object> token = this.userService.Register(u);

            return ResponseEntity
                    .ok(new ResponseFormat(ResponseType.USER_REGISTER_SUCCESS, token));
        } catch (Exception error) {

            error.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new ResponseFormat(ResponseType.FAIL, error.getMessage()));
        }
    }

    @PostMapping("/api/users/account/login")
    public ResponseEntity login (@RequestBody User u, Errors errors){
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                logger.warn(error.toString());
            }

            return ResponseEntity
                    .badRequest()
                    .body(new ResponseFormat(ResponseType.VALIDATE_ERROR, null));
        }

        try {
            HashMap<String, Object> token = this.userService.Login(u);

            return ResponseEntity
                    .ok(new ResponseFormat(ResponseType.USER_LOGIN_SUCCESS, token));
        } catch (Exception error) {
            error.printStackTrace();
            return ResponseEntity
                    .status(500)
                    .body(new ResponseFormat(ResponseType.FAIL, error.getMessage()));
        }
    }
}
