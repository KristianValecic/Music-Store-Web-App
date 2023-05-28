package hr.valecic.musicstorewebapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mediacategory")
@Data
public class MediaCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idcategory", nullable = false)
    private Long idcategory;
    @Basic
    @Column(name = "mediacategoryname", nullable = false, length = 255)
    private String mediacategoryname;


}
