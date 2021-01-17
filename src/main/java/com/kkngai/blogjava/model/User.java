package com.kkngai.blogjava.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author : kkngai
 * @created : 9/1/2021, 星期六
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Username may not be blank")
    private String username;

    private String avatar;

    @NotBlank(message = "Email may not be blank")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password may not be blank")
    private String password;

    @NotNull
    private int status;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

}
