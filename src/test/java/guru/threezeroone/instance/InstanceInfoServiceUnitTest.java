package guru.threezeroone.instance;

import guru.threezeroone.url.UrlService;
import org.junit.Assert;
import org.junit.Test;

public class InstanceInfoServiceUnitTest {

	InstanceInfoService service = new InstanceInfoService();
    
	@Test
	public void testGetRandomShortToken() {
		String hostnameAndIP = service.getHostnameAndIP();
		Assert.assertNotNull(hostnameAndIP);
	}
	
}
