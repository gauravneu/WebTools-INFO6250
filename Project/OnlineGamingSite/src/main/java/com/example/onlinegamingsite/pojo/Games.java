package com.example.onlinegamingsite.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "Games")
@Data
@NoArgsConstructor
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    private Genre genre;
    private boolean premium;
    private double price;
    private int quantity;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] gameImage;
    @Transient
    private String base64Encoded;

    @Override
    public boolean equals(Object obj) {
        Games games = (Games) obj;
        return this.name.equals(games.name) &&
                this.hashCode() == games.hashCode();
    }

    @Override
    public int hashCode() {
        return 1;
    }
}