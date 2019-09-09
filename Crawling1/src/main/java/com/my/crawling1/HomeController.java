package com.my.crawling1;

import java.awt.image.BufferedImage;
import java.beans.Encoder;
import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		
//		
//		String url = "http://browse.auction.co.kr/search?keyword=%ec%8b%a0%eb%b0%9c&itemno=&nickname=&frm=hometab&dom=auction&isSuggestion=No&retry=&Fwk=%ec%8b%a0%eb%b0%9c&acode=SRP_SU_0100&arraycategory=&encKeyword=%ec%8b%a0%eb%b0%9c&f=b:31215&s=4";
//		
//		ArrayList<Element> list = new ArrayList<Element>();
//		ArrayList<Element> list2 = new ArrayList<Element>();
//		
//		try {
//			Document doc = Jsoup.connect(url).get();
//			
//			Elements els = doc.select("div.section--module_wrap");
//			Elements title = doc.select("id.section--itemcard");
//			
//			System.out.println(title.toString());
//			for(Element el : els) {
//				list.add(el);
//			}
//			for(Element el : title) {
//				list2.add(el);
//			}
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		model.addAttribute("list",list);
//		model.addAttribute("list2",list2);
		String url = "https://sports.news.naver.com/kfootball/index.nhn";
		ArrayList<String> list = new ArrayList<String>();
//		ArrayList<String> imgAll = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements els = doc.select("div#rank_template1.home_box");
//			Elements imgs = els.select("img");
			for(Element el : els) {
				list.add(el.outerHtml());
			}
//			for(Element img : imgs ) {
//				String allImg = img.getElementsByAttribute("src").attr("src");
//				URL imgUrl = new URL(allImg);
//				BufferedImage jpg = ImageIO.read(imgUrl);
//				imgAll.add(allImg);
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list",list);
//		model.addAttribute("imgAll",imgAll);
		return "home";
	}
	
	
	@RequestMapping(value="/ddd", method=RequestMethod.GET)
	public String crawl(Model model, HttpServletRequest request) {
		String path = request.getRealPath("/imgs");
		
		String url = "http://corners.auction.co.kr/corner/UsedBest.aspx";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> imgList = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements element = doc.select(".used_galleryview_wrap li");
			System.out.println("element : " + element.size());
//			Elements imgs = element.tagName("img"); 
//			System.out.println("imgs : " + imgs.size());
//			System.out.println(imgs.toString());
			
//			int page = 0;
//			for(Element el : imgs) {
				
//				String imgAll = el.getElementsByAttribute("src").attr("src");
//				System.out.println(imgAll);
//				URL imgUrl = new URL(imgAll);
//				BufferedImage jpg = ImageIO.read(imgUrl);
//				File file = new File(File.separator + path+File.separator+page+".jpg");
//				ImageIO.write(jpg, "jpg", file);
				
//				imgList.add(el.attr("src"));
//				System.out.println(imgList.get(page));
//				page++;
//			}
			
			for(Element el : element) {
				list.add(el.html());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("list",list);
		model.addAttribute("imgList",imgList);
		return "ddd";
	}
	
}
