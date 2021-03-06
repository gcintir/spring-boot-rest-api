package springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootrestapi.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
