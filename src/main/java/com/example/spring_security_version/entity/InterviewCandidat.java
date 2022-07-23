package com.example.spring_security_version.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InterviewCandidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lien;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_candidat")
    private Candidat candidat;

}
