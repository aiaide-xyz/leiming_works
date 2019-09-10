package com.leiming.course_evaluation.controller;

import com.leiming.course_evaluation.validate.ImageCode;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RestController
public class ValidateCodeController {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private String sessionKey = "session_key_image_code";
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = createImage();
        sessionStrategy.setAttribute(new ServletWebRequest(request),sessionKey,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());

    }

    private ImageCode createImage() {
        int width = 67;
        int height = 23;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(new Color(255,255,255));
        graphics.fillRect(0,0,width,height);
        graphics.setColor(new Color(0,0,0));
        graphics.setFont(new Font("Time New Roman",Font.ITALIC,20));
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);

            graphics.drawLine(x,y,x + x1,y + y1);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            graphics.setColor(new Color(20+random.nextInt(100),20+random.nextInt(110),20+random.nextInt(100)));
            graphics.drawString(rand,13*i,16);
        }
        graphics.dispose();
        return new ImageCode(image,sRand,60);
    }
}
