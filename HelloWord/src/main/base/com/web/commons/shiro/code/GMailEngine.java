package com.web.commons.shiro.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.DeformedComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class GMailEngine extends ListImageCaptchaEngine
{
	/**
	 * 随机产生定义的颜色
	 * 
	 * @return
	 */
	private static Color[] getColors() {
		Color color[] = new Color[5];
		color[0] = new Color(32, 158, 25);
		color[1] = new Color(218, 42, 19);
		color[2] = new Color(31, 75, 208);
		color[3] = new Color(0, 102, 182);
		color[4] = new Color(171, 0, 85);
		return color;
	}
  protected void buildInitialFactories()
  {
	  // 图片和字体大小设置
      int minWordLength = 4;
      int maxWordLength = 4;
      int fontSize = 25;
      int imageWidth = 130;
      int imageHeight = 40;
      
      
      // 初始化种子
      WordGenerator dictionnaryWords = new RandomWordGenerator("0123456789abcdefghijklmnpqrstuvwxyz");
  	ImageDeformation backDef = new ImageDeformationByFilters(  new ImageFilter[0] );  
  	ImageDeformation textDef = new ImageDeformationByFilters( new ImageFilter[0] );  
  	ImageDeformation postDef = new ImageDeformationByFilters(  new ImageFilter[0] ); 
	RandomListColorGenerator colors = new RandomListColorGenerator(getColors());  
  
	TextPaster randomPaster = new DecoratedRandomTextPaster(new Integer(minWordLength), new Integer(maxWordLength), colors, new TextDecorator[0]);
	BackgroundGenerator background = new UniColorBackgroundGenerator(Integer.valueOf(imageWidth), Integer.valueOf(imageHeight), Color.white);
	
	FontGenerator font = new RandomFontGenerator(fontSize, fontSize, new Font[] { new Font("Antique Olive Compact", Font.BOLD, fontSize)});
	
	WordToImage word2image = new DeformedComposedWordToImage(font, background, randomPaster, backDef, textDef, postDef);
	
	addFactory(new GimpyFactory(dictionnaryWords, word2image));
  }
}