package com.Makushev.Makushev_Social_Twitter.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reels")
public class Reels {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String tittle;

    private String video;

    @ManyToOne
    private User user;
}
