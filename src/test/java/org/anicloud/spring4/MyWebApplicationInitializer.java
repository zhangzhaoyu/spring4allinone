package org.anicloud.spring4;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by zhaoyu on 15-4-6.
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
                new DispatcherServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
