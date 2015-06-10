package org.anicloud.spring4.interfaces.web;

import org.anicloud.spring4.application.dto.UserDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by zhaoyu on 15-6-9.
 * Spring MVC judge the Type of Converter by the Content-Type field from the http principle
 */
@RequestMapping(value = "/example")
@Controller
@SessionAttributes("user")
public class ExampleController extends BaseController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(ModelMap map) {
        map.addAttribute("user", new UserDto());
        return "example/register";
    }

    @RequestMapping(value = "handler11")
    public String handler1(@RequestParam(value = "userName", required = false) String userName,
                           @RequestParam("age") int age) {
        return null;
    }

    // for cookie information
    @RequestMapping("/handler12")
    public String handler12(@CookieValue(value = "sessionId", required = false) String sessionId,
                            @RequestParam("age") int age) {
        return null;
    }

    @RequestMapping(value = "/handler13")
    public String handler13(@RequestHeader("Accept-Encoding") String encoding,
                            @RequestHeader("Keep-Alive") long keepAlive) {
        return null;
    }

    @RequestMapping("/handler14")
    public String handler14(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userName = WebUtils.findParameterValue(httpServletRequest, "userName");
        httpServletResponse.addCookie(new Cookie("userName", userName));
        return null;
    }

    @RequestMapping("/handler15")
    public String handler15(HttpSession httpSession) {
        httpSession.setAttribute("sessionId", 123);
        return null;
    }

    @RequestMapping("/handler16")
    public String handler16(WebRequest request) {
        String userName = request.getParameter("userName");
        return null;
    }

    // httpServletRequest.getReader()
    @RequestMapping("/handler17")
    public void handler17(OutputStream stream) throws IOException {
        Resource resource = new ClassPathResource("/image.jpg");
        FileCopyUtils.copy(resource.getInputStream(), stream);
    }

    @RequestMapping("/handler18")
    public String handler18(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/handler19/{imageId}")
    public byte[] handler19(@PathVariable("imageId") String imageId) throws IOException {
        Resource resource = new ClassPathResource("/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return fileData;
    }

    // use StringHttpMessageConverter
    @RequestMapping(value = "/handler20")
    public String handler20(HttpEntity<String> httpEntity) {
        long contentLen = httpEntity.getHeaders().getContentLength();
        System.out.println(httpEntity.getBody());
        return null;
    }

    // use ByteArrayHttpMessageConverter
    @RequestMapping("/handler21/{imageId}")
    public ResponseEntity<byte[]> handler21(@PathVariable("imageId") String imageId) throws IOException {
        Resource resource = new ClassPathResource("/image/jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(resource.getInputStream());
        ResponseEntity<byte[]> responseEntity =
                new ResponseEntity<byte[]>(fileData, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/handler22")
    public ResponseEntity<UserDto> handler22(HttpEntity<UserDto> requestEntity) {
        UserDto userDto = requestEntity.getBody();
        System.out.println("on server : " + userDto.toString());
        UserDto dto = new UserDto(userDto.id, userDto.userName);
        dto.id = 11;
        return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
    }

    @RequestMapping(value = "/handler23")
    public String handler23(@ModelAttribute("user") UserDto dto) {
        return null;
    }

    @RequestMapping(value = "/handler24")
    public String handler24(ModelMap modelMap) {
        modelMap.addAttribute("name", "zhangzhaoyu");
        return null;
    }

    @RequestMapping("/handler25")
    public String handler25(ModelMap modelMap, SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return null;
    }

    @RequestMapping("/handler26")
    public String handler26(@ModelAttribute("user") UserDto userDto) {
        return null;
    }

    // Validator Result store in BindingResult or Errors class.
    @RequestMapping("/handler27")
    public String handler27(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> list = bindingResult.getAllErrors();
            System.out.println(list);
            return "example/register";
        } else {
            return null;
        }
    }
}
