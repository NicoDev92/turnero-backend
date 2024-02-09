package com.nicode.turnero.entities;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.*;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "turns_config")
public class TurnsConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "turns_config_days", joinColumns = @JoinColumn(name = "turns_config_id"))
    @MapKeyColumn(name = "day_of_week")
    @Column(name = "available_hours")
    @Temporal(TemporalType.TIME)
    private Map<DayOfWeek, Set<String>> availableHoursByDay;

}
