package com.example.demo.security;

public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(encode(rawPassword).equals(encodedPassword))
        return true;
        else
            return false;
    }
}
