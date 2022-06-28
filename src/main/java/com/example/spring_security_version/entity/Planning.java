package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String date_debut;

    private String date_fin;

    @OneToMany(mappedBy = "planning")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<Utilisateur> utilisateurs;

    @ManyToOne
    @JoinColumn(name = "id_responsable_rh")
    private ResponsableRH responsableRH;

}
