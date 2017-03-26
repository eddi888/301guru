package guru.threezeroone.url;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UrlRepository extends CrudRepository<Url, String>  {
	
	@EnableScan 
	@EnableScanCount
	public Page<Url> findAll(Pageable pageable);
	
	@EnableScan
	public Url findOneByShortToken(String shortToken);
	
	@EnableScan
	public Url findOneByFullUrl(String fullUrl);
	
}
