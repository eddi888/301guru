package guru.threezeroone.webuser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebuserRepository extends CrudRepository<Webuser, Long> {
	
    Page<Webuser> findAll(Pageable pageable);
    
}
