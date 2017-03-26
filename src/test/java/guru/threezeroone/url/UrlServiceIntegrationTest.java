package guru.threezeroone.url;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.threezeroone.DynamoDBConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes={UrlServiceIntegrationTest.class, UrlRepository.class, UrlService.class, DynamoDBConfig.class})
public class UrlServiceIntegrationTest {

	@Autowired
	UrlRepository repositiory;
	
	@Autowired
	UrlService service;
	
	@Before
    public void setUp() {
		
	}
	
	@Test
	public void testUniqueShortToken() {
		Url url1 = service.getOrCreateUrl("http://www.example.org/");
		Url url2 = service.getOrCreateUrl("http://www.example.org/");
		Assert.assertEquals(url1.getShortToken(), url2.getShortToken());
	}
	
	@Test
	public void testGetFullUrl() {
		Url url = service.getOrCreateUrl("http://www.example.com/");
		String fullUrl = service.getFullUrl(url.getShortToken());
		Assert.assertEquals("http://www.example.com/", fullUrl);
	}
	
	@Test
	public void testCountClick() {
		Url url = service.getOrCreateUrl("http://www.example.de/");
		url = repositiory.findOneByShortToken(url.getShortToken());
		long before = url.getClickCount();
		
		service.countClick(url.getShortToken());
		
		Url urlAfter = repositiory.findOneByShortToken(url.getShortToken());
		long after = urlAfter.getClickCount();
		
		Assert.assertEquals(before+1, after);
	}

}
