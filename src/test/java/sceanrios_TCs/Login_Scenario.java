package sceanrios_TCs;

import generic_library.Base_Class;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;


import PageObjectRepository.POM_LoginScenario;


public class Login_Scenario extends Base_Class{

//SoftAssert assert1 = new SoftAssert();
static Logger log = Logger.getLogger(Login_Scenario.class);
	
	@Test(dataProvider = "DP_invalid_login", dataProviderClass = DataProviderObjects.DP_LoginScenario.class, groups={"Regression"})
	public void invalid_login(String TC_ID, String Order, String Uname, String Pwd, String expected ) throws Exception
	{
		log.info("Starting invalid_login scenario"+ TC_ID+" "+ Order);
		Initialize_browser();
		//Assert.assertEquals(expected, actual);
		log.info("Browser Initialized"+ TC_ID+" "+ Order);
		
		POM_LoginScenario pom_invalid  = new POM_LoginScenario(driver);
		pom_invalid.execute_common_methods_log(Uname, Pwd);	
		if (driver.getTitle().equalsIgnoreCase("Buy Books Online | Online Bookstore India | Online Book Shopping | Free Shipping Across India"))
		{
			log.error("The user has  loggedin, the page the user currently is " +driver.getTitle());
			snapshotmethod("valid_login", TC_ID, Order);
			tear_down();
			Assert.fail();
			
		}
		snapshotmethod("valid_login", TC_ID, Order);
		String actual = pom_invalid.get_invalid_log_msg();
		//assert1.assertEquals(actual, expected);
		if(actual.equalsIgnoreCase(expected))
		{
			log.info("The validation has passed");
		}
		else
		{
			log.error("THe validation has failed");
		}
		tear_down();
		//assert1.assertAll();
		log.info("Completed invalid_login scenario"+ TC_ID+" "+ Order);
	}	
	@Test (dataProvider = "DP_valid_login", dataProviderClass = DataProviderObjects.DP_LoginScenario.class , groups={"SmokeTest","Regression"})
	public void valid_login(String TC_ID, String Order, String Uname, String Pwd, String expected ) throws Exception
	{
		log.info("Starting valid_login scenario"+ TC_ID+" "+ Order);
		Initialize_browser();
		log.info("Browser Initialized for valid scenario"+ TC_ID+" "+ Order);
		POM_LoginScenario pom_valid  = new POM_LoginScenario(driver);
		pom_valid.execute_common_methods_log(Uname, Pwd);
		if (!driver.getTitle().equalsIgnoreCase("Buy Books Online | Online Bookstore India | Online Book Shopping | Free Shipping Across India"))
		{
			log.error("The user has failed to login, the page the user currently is " +driver.getTitle());
			snapshotmethod("valid_login", TC_ID, Order);
			tear_down();
			Assert.fail();
			
		}
		snapshotmethod("valid_login", TC_ID, Order);
		String actual = pom_valid.get_valid_log_msg();
		//assert1.assertEquals(actual, expected);
		if(actual.equalsIgnoreCase(expected))
		{
			log.info("The validation has passed for valid scenario" + TC_ID+" "+ Order);
		}
		else
		{
			log.error("The validation has failed for valid scenario" + TC_ID+" "+ Order);
		}
		pom_valid.click_signout();
		tear_down();
		//assert1.assertAll();
	}

}
