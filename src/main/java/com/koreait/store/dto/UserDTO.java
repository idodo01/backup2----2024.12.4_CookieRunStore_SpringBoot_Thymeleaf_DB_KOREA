package com.koreait.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {
    @NotBlank
    @Length(min = 4, max = 15)
    @Pattern(regexp = "^[a-z][0-9a-zA-Z]*$")
    private String id;
    @NotBlank
    @Pattern(regexp = "^[0-9a-zA-Z~!@#$%^&*()_=+.-]{4,10}")
    private String password;
    @NotBlank
    @Pattern(regexp = "^(010|011|017|019|018)-[0-9]{3,4}-[0-9]{4}$")
    private String tel;
    @NotBlank 
    @Email
    private String email;
    private String nickname;
//    private String profileImage;

    public void setTel(String tel) {
        this.tel = tel.replace(",", "-");
    }

    public void setEmail(String email) {
        this.email = email.replace(",", "@");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("TEMP"));
    }

    @Override
    public String getUsername() {
        return this.id;
    }
}
