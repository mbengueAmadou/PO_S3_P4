package model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "Accord_signed")
public class Accord_signed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accord_id;

    private String partner_id;

    private Long type_accord;

    private Date created_date;

    private Boolean status;
}
