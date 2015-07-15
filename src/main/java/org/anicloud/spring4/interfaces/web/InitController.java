package org.anicloud.spring4.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by zhaoyu on 15-4-7.
 */
@Controller
public class InitController extends BaseController {
    private final static Logger LOG = LoggerFactory.getLogger(InitController.class.getName());

    @Autowired
    private CookieLocaleResolver cookieLocaleResolver;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model map, Locale locale, HttpSession session) {

        session.setAttribute("locale", locale.getLanguage());
        map.addAttribute("user", "zhangzhaoyu");
        LOG.info("locale is {}.", cookieLocaleResolver.getCookieName());
        LOG.info("language : ", locale.getLanguage());

        return "login";
    }

    @RequestMapping(value = "/websocketpage")
    public String websocketPage() {
        return "websocket";
    }

    public String initWeb(ServletRequest request) {
        String result = "";
        WebApplicationContext context = RequestContextUtils.getWebApplicationContext(request);
        String webApplicationName = DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE;
        return result;
    }
}
