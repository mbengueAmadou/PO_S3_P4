package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "Partner")
public class Partner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom_complete;

    private String type;

    private String last_modified_by;

    private String created_date;

    private String archeve;

    private Boolean status;
}
