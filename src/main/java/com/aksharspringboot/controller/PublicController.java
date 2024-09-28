package com.aksharspringboot.controller;


import com.aksharspringboot.dto.Response;
import com.aksharspringboot.dto.StudentDto;
import com.aksharspringboot.model.UserVo;
import com.aksharspringboot.repository.UserRepository;
import com.aksharspringboot.service.StudentService;
import com.aksharspringboot.service.UserDetailsServiceImp;
import com.aksharspringboot.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PublicController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/logoutt")
    public ResponseEntity<Response> serverHealthCheck(HttpServletResponse response)
    {
        // Create a cookie with the same name to delete it
        ResponseCookie deleteCookie = ResponseCookie.from("Authentication", "")
                .httpOnly(true)
                .secure(true) // Use true if using HTTPS
                .path("/") // Ensure this matches the path of the original cookie
                .maxAge(0) // Expire the cookie immediately
                .sameSite("Strict") // Match SameSite attribute
                .build();

        // Add the delete cookie to the response
        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
        return new ResponseEntity<Response>(new Response("Logout Successfully",null,true),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserVo user, HttpServletResponse response) {
        try {
            this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            String username = this.userDetailsServiceImp.loadUserByUsername(user.getUsername()).getUsername();

            String jwt = "Bearer" + jwtUtil.generateToken(username);

            ResponseCookie jwtCookie = ResponseCookie.from("Authentication", jwt)
                    .httpOnly(true)
                    .secure(false) // Use this if your app is served over HTTPS
                    .path("/")
                    .maxAge(24 * 60 * 60) // Set cookie expiration time
                    .sameSite("Lax") // SameSite attribute for CSRF protection
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            return new ResponseEntity<>(new Response("Valid User Name and password", Map.of("username",username,"role",this.userRepository.findByUsername(username).get(0).getRole()), true), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response("Username or password invalid.", null, false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserVo user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return new ResponseEntity<>(new Response("Registered SuccesFully",null,true),HttpStatus.OK);
    }

    @PostMapping("/register/student")
    public ResponseEntity<Response> registerStudent(@RequestBody StudentDto studentDto)
    {
        Response response=this.studentService.addStudent(studentDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/health-check")
    public ResponseEntity<Response> healthCheck()
    {
        return new ResponseEntity<>(new Response("Hello world",null,true),HttpStatus.OK);
    }

}
