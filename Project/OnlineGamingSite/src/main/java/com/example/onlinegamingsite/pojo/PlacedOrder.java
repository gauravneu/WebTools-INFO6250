package com.example.onlinegamingsite.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

;

@Component
@Entity
@Data
@NoArgsConstructor
public class PlacedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String address;                                     //user.address
    private boolean delivered;                                  //false
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "prodquant1")
    @CollectionTable(name = "ordergamelist1")
    private Map<Games, Integer> orders = new HashMap<>();       //user : cart added in user can be moved directly here
    @ManyToOne
    private User user;                                          //user
    private double amountPaid;                                  //payableAmount
}
