package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResponsableRH extends Utilisateur{

    @OneToMany(mappedBy = "responsable_rh")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private List<Contrat> contrats;

    @OneToMany(mappedBy = "responsable_rh")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private List<Candidature> candidatures;

    @OneToMany(mappedBy = "responsableRH")
    @Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private List<Planning> plannings;

}
