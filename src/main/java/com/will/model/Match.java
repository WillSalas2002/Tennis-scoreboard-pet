package com.will.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Matches", schema = "tennis")
public class Match {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
