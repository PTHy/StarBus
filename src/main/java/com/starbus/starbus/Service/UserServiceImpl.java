package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.User;
import com.starbus.starbus.Repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HashMap<String, Object> Login(User user) {
        user.setPassword(user.getPassword());
        return this.userRepository.findByIdAndPassword(user.getId(), user.getPassword())
                .map(fu -> {
                    String jwtString = JwtTokenByUser(fu);
                    HashMap<String, Object> result = new HashMap<>();
                    result.put("token", jwtString);
                    return result;
                })
                .orElse(null);
    }

    @Override
    public HashMap<String, Object>  Register(User user) throws Exception{
        Optional<User> fu = this.userRepository.findById(user.getId());
        if (fu.isPresent())
            throw new Exception("이미 존재하는 유저입니다");

        user.setPassword(user.getPassword());

        this.userRepository.save(user);

        String jwtString = this.JwtTokenByUser(user);

        HashMap<String, Object> result = new HashMap<>();

        result.put("token", jwtString);

        return result;
    }

    private String JwtTokenByUser(User user) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
