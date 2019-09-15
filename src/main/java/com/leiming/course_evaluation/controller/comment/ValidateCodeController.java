package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.service.StudentService;
import com.leiming.course_evaluation.validate.code.Captcha;
import com.leiming.course_evaluation.validate.code.GifCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ValidateCodeController {

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @GetMapping("/getGifCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setContentType("image/gif");
        Captcha captcha = new GifCaptcha(108, 33, 4);
        captcha.out(response.getOutputStream());
        request.getSession().setAttribute("code",captcha.text().toLowerCase());
    }

}
