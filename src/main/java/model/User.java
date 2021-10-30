package model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "User")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String adresse;

    private String telephone;

    private String password;

    private String profil;

    private Date created_date;

    private Date last_modified_date;

    private Boolean archeved;
}
