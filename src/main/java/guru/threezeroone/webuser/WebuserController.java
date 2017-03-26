package guru.threezeroone.webuser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebuserController {
	
	private static Log log = LogFactory.getLog(WebuserController.class);
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }
    
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public ModelAndView generateShortToken(@RequestParam(value = "fullUrl") String fullUrl) {
    	
        //TODO impl
    	
        return new ModelAndView("index");
    }
       
}
