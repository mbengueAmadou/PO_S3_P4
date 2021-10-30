package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Accord")
public class Accord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private String file;

    private Long user_id;

    private String approved_by;

    private Date approved_date;

    private Boolean status;
}
