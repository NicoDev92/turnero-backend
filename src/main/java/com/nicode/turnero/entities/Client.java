package com.nicode.turnero.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clients")
public class Client extends Person {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turn_id")
    @JsonBackReference
    private Turn turn;
}
