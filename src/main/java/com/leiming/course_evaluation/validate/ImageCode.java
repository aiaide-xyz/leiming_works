package com.leiming.course_evaluation.validate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {
    private BufferedImage image;
    private String code;
    private LocalDateTime localDateTime;
    public ImageCode(BufferedImage image,String code,int localDateTime){
        this.image = image;
        this.code = code;
        this.localDateTime = LocalDateTime.now().plusSeconds(localDateTime);
    }
    public ImageCode(BufferedImage image, String code, LocalDateTime localDateTime) {
        this.image = image;
        this.code = code;
        this.localDateTime = localDateTime;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
