package com.interactive.data;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.interactive.data.control.HomeController;
import com.interactive.data.model.GameStatusVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml", "file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class HomeControllerTest {
	Model model;
	Locale locale;
	GameStatusVO gameObj;
	@Autowired
	HomeController homeController;
	
	@Before
	public void init(){
		this.model = new ExtendedModelMap();
		this.locale = Locale.getDefault();
		this.gameObj = new GameStatusVO();
	}
	@Test
	public void testHome() throws Exception{
 		String home = homeController.home(this.locale, this.model);
		assertEquals("home", home);
		assertNotNull(home);
	}

	@Test
	public void testGuess() throws Exception{
		//HomeController homeController = new HomeController();
		gameObj.setActual(0);
		String home = homeController.guess(this.locale, this.model, "4", gameObj);
		assertEquals("home", home);
		assertNotNull(home);
	}
	
	@After
	public void unwind(){
		this.model = null;
		this.locale = null;
		this.gameObj = null;
	}
}
