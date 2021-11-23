package apap.tugasakhir.siRetail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_cabang")

public class ItemCabangModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemCabang;

    @NotNull
    @Column(nullable = false)
    private String uuidItem;

    @NotNull
    @Size(max = 50)
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(nullable = false)
    private Integer harga;

    @NotNull
    @Column(nullable = false)
    private Integer stok;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String kategori;

    @NotNull
    @Column(nullable = false)
    private Integer idPromo;

    //Relasi dengan CabangModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_cabang", referencedColumnName = "idCabang", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CabangModel cabang;
}
