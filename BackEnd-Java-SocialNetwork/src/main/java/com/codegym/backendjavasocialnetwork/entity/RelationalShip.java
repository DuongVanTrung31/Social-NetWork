package com.codegym.backendjavasocialnetwork.entity;

import com.codegym.backendjavasocialnetwork.entity.enums.StatusRelationalShip;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RelationalShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "target_user")
    private User user2;

    @Enumerated(EnumType.STRING)
    private StatusRelationalShip statusRelationalShip;
}
