package com.will.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Match", schema = "tennis")
public class Match {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player1", referencedColumnName = "id")
    private Player player1;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player2", referencedColumnName = "id")
    private Player player2;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner", referencedColumnName = "id")
    private Player winner;
}
