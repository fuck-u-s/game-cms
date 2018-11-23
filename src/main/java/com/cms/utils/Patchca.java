package com.cms.utils;


import org.patchca.filter.ConfigurableFilterFactory;
import org.patchca.filter.library.AbstractImageOp;
import org.patchca.filter.library.WobbleImageOp;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.text.renderer.BestFitTextRenderer;
import org.patchca.word.RandomWordFactory;

import java.awt.image.BufferedImageOp;
import java.util.ArrayList;
import java.util.List;

/**
 * 方法描述:生成验证码
 *
 * @author 小刘
 * @version v1.0
 * @date 2015/11/5
 */
public class Patchca {

   public static ConfigurableCaptchaService createImage(){

       ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
       cs.setWidth(80);
       cs.setHeight(32);

       RandomFontFactory fontFactory = new RandomFontFactory();
       fontFactory.setMaxSize(28);
       fontFactory.setMinSize(24);
       cs.setFontFactory(fontFactory);

       RandomWordFactory wordFactory = new RandomWordFactory();
       wordFactory.setCharacters("abcdefghkmnpqstwxyz23456789");
       wordFactory.setMaxLength(4);
       wordFactory.setMinLength(4);
       cs.setWordFactory(wordFactory);

       ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
       List<BufferedImageOp> filters = new ArrayList<>();
       WobbleImageOp wobbleImageOp = new WobbleImageOp();
       wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_CLAMP);
       wobbleImageOp.setxAmplitude(2.0);
       wobbleImageOp.setyAmplitude(1.0);
       filters.add(wobbleImageOp);
       filterFactory.setFilters(filters);

       cs.setFilterFactory(filterFactory);

       // 文字渲染器设置
       BestFitTextRenderer textRenderer = new BestFitTextRenderer();
       textRenderer.setBottomMargin(3);
       textRenderer.setTopMargin(3);
       cs.setTextRenderer(textRenderer);
       return cs;
   }
}
