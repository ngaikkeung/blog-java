package com.kkngai.blogjava.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author : kkngai
 * @created : 17/1/2021, 星期日
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "user_id")
    @NotNull
    private long userId;

    @NotNull
    private String title;

    @NotNull
    private String description;

    private String content;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @NotNull
    private int status;
}
