package com.example.demo.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "Login";  // must match Login.html
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "Register";  // must match Register.html
    }
}
