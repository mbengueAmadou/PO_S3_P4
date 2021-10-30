package Repository;


import model.Accord_signed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Accord_signedRepository extends JpaRepository<Accord_signed, Long> {
}
