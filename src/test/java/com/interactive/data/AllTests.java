package com.interactive.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ HomeControllerTest.class, TestExtraInfo.class,
		TestGameLogic.class, TestRandomNumberGenerator.class })
public class AllTests {

}
