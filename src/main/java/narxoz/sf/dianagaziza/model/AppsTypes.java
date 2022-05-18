package narxoz.sf.dianagaziza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppsTypes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

}

