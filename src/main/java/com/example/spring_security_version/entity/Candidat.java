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
public class Candidat extends Utilisateur{

    private String cv;

    private String lettre_motivation;

    @OneToMany(mappedBy = "candidat")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    private List<Candidature> candidatures;

    @OneToMany(mappedBy = "candidat")
    @Getter( onMethod = @__(@JsonIgnore))
    @JsonIgnore
    private List<DemandeCandidature> demandeCandidatures;

    @OneToMany(mappedBy = "candidat")
    @Getter( onMethod = @__(@JsonIgnore))
    private List<InterviewCandidat> interviewCandidats;

}
