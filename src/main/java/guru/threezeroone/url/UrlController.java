package guru.threezeroone.url;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UrlController {
	
	private static Log log = LogFactory.getLog(UrlController.class);
    
	@Autowired
	private UrlService urlService;
	
	@Value("${app.url:http://localhost:8080}")
	private String appUrl;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model, Locale locale) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView generateShortToken(@RequestParam(value = "fullUrl") String fullUrl) {
    	if (fullUrl==null || fullUrl.length()==0){
    		return new ModelAndView("index");
    	}
    	
    	Url url = urlService.getOrCreateUrl(fullUrl);
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("url", url);
        mav.addObject("shortUrl", urlService.getShortUrl(url.getShortToken()));
        mav.addObject("statsUrl", urlService.getStatsUrl(url.getShortToken()));
        return mav;
    }
    
    @RequestMapping(value = "/stats/{shortToken}", method = RequestMethod.GET)
    public ModelAndView stats(@PathVariable(value = "shortToken") String shortToken) {
    	Url url = urlService.findOneByShortToken(shortToken);
    	
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("url", url);
        mav.addObject("shortUrl", urlService.getShortUrl(url.getShortToken()));
        mav.addObject("statsUrl", urlService.getStatsUrl(url.getShortToken()));
        return mav;
    }
    
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ModelAndView statsList() {
    	Page<Url> url = urlService.findAll(new PageRequest(0, 1000));
    	//TODO pageing and sorting
    	ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("appUrl", appUrl);
        mav.addObject("urlList", url.getContent());
        return mav;
    }
    
    @RequestMapping(value = "/{shortToken}", method = RequestMethod.GET)
    public ModelAndView shortToken(@PathVariable(value = "shortToken") String shortToken) {
    	final String fullUrl = urlService.getFullUrl(shortToken);
    	
    	if (fullUrl!=null) {
            // FOUND AND FORWARD 301
    		urlService.countClick(shortToken);
    		RedirectView red = new RedirectView(fullUrl, false);
            return new ModelAndView(red);
    	} else {
            // NOT FOUND 404
    		String errmsg = "Your url '"+urlService.getShortUrl(shortToken)+"' can not be found.";
    		
    		ModelAndView mav = new ModelAndView();
    		mav.setViewName("index");
    		mav.addObject("notFound", errmsg);
    		mav.setStatus(HttpStatus.NOT_FOUND);
    		return mav;
    	}
    }
        
}
