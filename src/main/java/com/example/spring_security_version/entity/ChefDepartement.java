package com.example.spring_security_version.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class ChefDepartement extends Utilisateur {



    //private Integer nbEmpDept ;

    //@OneToMany(mappedBy = "chef_departement")
    //@Getter( onMethod = @__(@JsonIgnore))
    //@JsonIgnore
    //private List<Conge> conges;



}
