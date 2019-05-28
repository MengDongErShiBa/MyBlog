package com.mdesb.controller.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;

/**
 * 公用控制器
 * @author 孟冬二十八。
 *
 * 2019年5月23日-上午10:34:58
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private DefaultKaptcha captchaProducer;
	
	/**
	 * 获取验证码
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws Exception
	 */
    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        //字节数组输出流
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String verifyCode = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            //保存到IO流
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaOutputStream = imgOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
