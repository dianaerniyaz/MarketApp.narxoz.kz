package narxoz.sf.dianagaziza.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "apps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "age_limit")
    private int age_limit;

    @Column(name = "memory_size")
    private float memory_size;

    @Column(name = "estimation")
    private float estimation;

    @ManyToOne()
    private AppsTypes types;

}

