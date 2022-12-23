package com.example.onlinegamingsite.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@Entity(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String address;
    private String password;
    private String role;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PlacedOrder> orderedGamesList = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "prodquant")
    @CollectionTable(name = "cartgamelist")
    private Map<Games, Integer> cartGameMap = new HashMap<>();
    @Transient
    private String repeatPassword;
}