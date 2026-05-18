package com.app.priorix.model.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

@Document(collection = "opt_code")
public class OtpCode {
    @Id
    private String id;
    private String email;
    private String code;
    private LocalDateTime expirationTime;

    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }



    
}
