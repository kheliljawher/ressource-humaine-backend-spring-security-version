package com.example.spring_security_version.entity;

import com.example.spring_security_version.serializers.ListUtilisateurSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "departement")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    @JsonSerialize(using = ListUtilisateurSerializer.class)
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "departement")
    @Getter( onMethod = @__(@JsonIgnore))
    @JsonIgnore
    private List<Candidature> candidatures;
}
