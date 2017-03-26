package guru.threezeroone.url;

import org.junit.Assert;
import org.junit.Test;

public class UrlServiceUnitTest {
	
    UrlService service = new UrlService();
    
	@Test
	public void testGetRandomShortToken() {
		String shortToken = service.getRandomShortToken();
		Assert.assertNotNull(shortToken);
		Assert.assertEquals(6, shortToken.length());
	}
	
}
