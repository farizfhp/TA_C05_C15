package apap.tugasakhir.siRetail.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value={"listCabang"},allowSetters = true)

public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotNull
    @Size(max =50)
    @Column (name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(max =50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Lob
    @Size(max =100)
    @Column(name = "password", nullable = false)
    private String password;

    //Relasi dengan CabangModel
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CabangModel> listCabang;

    //Relasi dengan RoleModel
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_role", referencedColumnName = "idRole", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RoleModel role;

}
