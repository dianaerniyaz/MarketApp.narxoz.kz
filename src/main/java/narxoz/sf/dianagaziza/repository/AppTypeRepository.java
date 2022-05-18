package narxoz.sf.dianagaziza.repository;


import narxoz.sf.dianagaziza.model.AppsTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppTypeRepository extends JpaRepository<AppsTypes, Long> {
}
