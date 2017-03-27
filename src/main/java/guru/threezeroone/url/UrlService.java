package guru.threezeroone.url;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;

@Service
public class UrlService {
	
	@Autowired
	private UrlRepository repository;
	
	@Value("${app.url:http://localhost:8080}")
	private String appUrl;
	
	public Url getOrCreateUrl(String fullUrl) {
		Url url = repository.findOneByFullUrl(fullUrl);
		if (url==null) {
			url = generateNewUrl(fullUrl);
		}
		url.setShortenCount(url.getShortenCount()+1);
		repository.save(url);
		return url;
	}
	
	protected Url generateNewUrl(String fullUrl){
		Url url = null;
		int attempt = 0;
		while(attempt<10){
			String randomToken = getRandomShortToken();
			url = repository.findOneByShortToken(randomToken);

			if (url==null) {
				url = new Url(randomToken, fullUrl);
				url = repository.save(url);
				break;
			}
			attempt++;
		}
		
		if(url==null){
			throw new InternalServerErrorException("can not find unused random token");
		} else{
			return url;
		}
	}
	
	public String getShortUrl(String shortToken){
		return appUrl+"/"+shortToken;
	}
	
	public String getStatsUrl(String shortToken){
		return appUrl+"/stats/"+shortToken;
	}
	
	public String getFullUrl(String shortToken){
		Url url = repository.findOneByShortToken(shortToken);
		if (url!=null) {
			return url.getFullUrl();
		} else {
			return null;
		}
	}
	
	public void countClick(String shortToken){
		Url url = repository.findOneByShortToken(shortToken);
		if (url!=null) {
			url.setClickCount(url.getClickCount()+1);
			repository.save(url);
		}
	}
	
	
	public String getRandomShortToken(){
		final Random random = new Random();
		final String charSet = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789";
		final StringBuffer shortToken = new StringBuffer();
		
		for (int i=0; i<6; i++) {
			shortToken.append(charSet.charAt(random.nextInt(charSet.length())));
		}
		
		return shortToken.toString();
	}
	
	public Url findOneByShortToken(String shortToken) {
		return repository.findOneByShortToken(shortToken);
	}
	
	public Page<Url> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
}
