package com.henrique.to_do_list.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Entity
@Table(name = "users") // user is a key-word, should not use.
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "email", length = 80, nullable = false)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    //@Table and @Column are not necessary. Just used for learning purposes

}
