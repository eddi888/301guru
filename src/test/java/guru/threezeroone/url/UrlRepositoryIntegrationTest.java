package guru.threezeroone.url;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.threezeroone.DynamoDBConfig;


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@ContextConfiguration(classes={UrlRepositoryIntegrationTest.class, DynamoDBConfig.class})
public class UrlRepositoryIntegrationTest {

	@Autowired
	UrlRepository repository;
		
	@Before
    public void setUp() {
        
	}
	
	@Test
	public void testCRUD() {
		// CREATE
        Url url1 = new Url();
        url1.setFullUrl("http://www.example.org");
        url1.setShortToken("1AbC123");
        repository.save(url1);
        
        Url url2 = new Url();
        url2.setFullUrl("http://www.example.com");
        url2.setShortToken("1DeF456");
        repository.save(url2);
        
        
        // READ by short token
        Url url3 = repository.findOneByShortToken("1AbC123");
        Assert.assertNotNull(url3);
        Assert.assertEquals(url1.getFullUrl(), url3.getFullUrl());
        Assert.assertEquals(url1.getShortToken(), url3.getShortToken());
        Assert.assertEquals(url1.getClickCount(), url3.getClickCount());

        // READ by full url
        Url url4 = repository.findOneByFullUrl("http://www.example.com");
        Assert.assertNotNull(url4);
        Assert.assertEquals(url2.getFullUrl(), url4.getFullUrl());
        Assert.assertEquals(url2.getShortToken(), url4.getShortToken());
        Assert.assertEquals(url2.getClickCount(), url4.getClickCount());
        
        // UPDATE
        url3.setClickCount(99);
        repository.save(url3);
        Url url5 = repository.findOneByShortToken("1AbC123");
        Assert.assertEquals(99, url5.getClickCount());
        
        // DELETE
        repository.delete(url1);
        repository.delete(url2);

        // READ DELETED
        Url url6 = repository.findOneByShortToken(url1.getShortToken());
        Assert.assertNull(url6);
        Url url7 = repository.findOneByShortToken(url2.getFullUrl());
        Assert.assertNull(url7);
	}
	
	@Test
	public void testPage() {
		// CREATE
        Url url1 = new Url();
        url1.setFullUrl("http://www.example.de");
        url1.setShortToken("1GhI789");
        repository.save(url1);
        
        Url url2 = new Url();
        url2.setFullUrl("http://www.example.net");
        url2.setShortToken("1JkL012");
        repository.save(url2);
        
        // READ PAGE
        Page<Url> page = repository.findAll(new PageRequest(0, 10));
        Assert.assertNotNull(page);
        Assert.assertEquals(10, page.getSize());
        
        // DELETE
        repository.delete(url1);
        repository.delete(url2);
	}
	

}
