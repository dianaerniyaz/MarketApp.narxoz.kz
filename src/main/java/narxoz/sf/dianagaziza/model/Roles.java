package narxoz.sf.dianagaziza.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roless")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roles_id;

    @Column(name="role")
    private String role;

    @Override
    public String getAuthority(){
        return role;
    }

}

