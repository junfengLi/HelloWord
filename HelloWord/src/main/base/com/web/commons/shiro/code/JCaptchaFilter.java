package com.web.commons.shiro.code;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class JCaptchaFilter extends OncePerRequestFilter
{
	// 创建Random对象
		static Random random = new Random();
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
  {
    response.setDateHeader("Expires", 0L);
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setContentType("image/jpeg");

    String id = request.getRequestedSessionId();
    BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);

    ServletOutputStream out = response.getOutputStream();
	// 获取图形上下文
	Graphics2D g = (Graphics2D) bi.getGraphics();
	// 话边框
	g.setColor(Color.white);
	g.setColor(Color.BLACK);
	int width = bi.getWidth();
	int height = bi.getHeight();
    // 随机产生干扰点，并用不同的颜色表示，事图像的认证码不易被其他程序探测到
	for (int i = 0; i < 100; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		// 随机画各种颜色的线
		g.setColor(color);
		g.drawOval(x, y, 0, 0);
	}
	// 画干扰线
	for (int i = 0; i < 15; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int x1 = random.nextInt(width);
		int y1 = random.nextInt(height);
		Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		// 随机画各种颜色线
		g.setColor(color);
		g.drawLine(x, y, x1, y1);
	}
	// 图像生效
	g.dispose();
    ImageIO.write(bi, "jpg", out);
    try {
      out.flush();
    } finally {
      out.close();
    }
  }
}