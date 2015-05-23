package org.anicloud.spring4.interfaces.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhaoyu on 15-4-9.
 */
@RequestMapping("/sample/{ownerId}")
public class SampleController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SampleController.class.getName());

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView showDetail(@PathVariable("ownerId") String ownerId, @PathVariable("userId") String userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/showDetail");
        modelAndView.addObject("userName", "zhangzhaoyu");
        return modelAndView;
    }
}
