package narxoz.sf.dianagaziza.repository;

import narxoz.sf.dianagaziza.model.Apps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<Apps, Long> {
}