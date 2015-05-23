package org.anicloud.spring4.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyu on 15-5-22.
 */
@Controller
@RequestMapping(value = "/chat")
public class ChatController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
    private static List<String> emails = new ArrayList<String>();

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        LOG.info("chat socket login page!!!");
        return "login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model, String email) {
        LOG.info("login email is : {}", email);
        HttpSession session = request.getSession(false);
        session.setAttribute("email", email);
        emails.add(email);
        model.addAttribute("emails", emails);
        return "chat";
    }
}
