package sceanrios_TCs;

import generic_library.Base_Class;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import PageObjectRepository.POM_Buynow;
import PageObjectRepository.POM_HomePage;
import PageObjectRepository.POM_LoginScenario;
import PageObjectRepository.POM_Online_shopping;
import PageObjectRepository.POM_shopping;

public class Cart_Scenario extends Base_Class {
	static Logger log = Logger.getLogger(Search_Scenario.class);
	@Test(dataProvider = "DP_addcart",dataProviderClass = DataProviderObjects.DP_CartScenario.class)
	public void addcart(String TC_ID, String Order, String Uname, String Pwd, String Search_Item, String Quantity, String expected ) throws Exception
	{
		//SoftAssert assert1 = new SoftAssert();		
		log.info("Intializing browser for Cart scenario : add cart"+ TC_ID+ "-"+ Order);
		Initialize_browser();
		log.info("Intializing browser for Cart scenario :add cart has completed"+ TC_ID+ "-"+ Order);
		POM_LoginScenario pom_login = new POM_LoginScenario(driver);
		pom_login.execute_common_methods_log(Uname, Pwd);
		if (!driver.getTitle().equalsIgnoreCase("Buy Books Online | Online Bookstore India | Online Book Shopping | Free Shipping Across India"))
		{
			log.error("The user has failed to login, the page the user currently is " +driver.getTitle() +" - "+TC_ID +" - "+ Order);
			tear_down();
			Assert.fail();
			
		}
		//System.out.println("Login Complete");
		POM_HomePage pom_home = new POM_HomePage(driver);
		pom_home.execute_comm_methods(Search_Item);
		//System.out.println("Item found");
		pom_home.click_img();
		//System.out.println("item selected");
		log.info("The book searched has been found and selected " +TC_ID +" - "+ Order);
		POM_Buynow pom_buy = new POM_Buynow(driver);
		pom_buy.click_buynow();
		log.info("Buy now functionality completed " +TC_ID +" - "+ Order);
		//System.out.println("buynow successfull");
		POM_shopping pom_shopping = new POM_shopping(driver);
		pom_shopping.Enter_quantity(Quantity);
		pom_shopping.get_booktext();
		pom_shopping.click_redifflink();
		log.info("Book has been added to the cart" +TC_ID +" - "+ Order);
		//System.out.println("Get book text and quantity completed");
		POM_Online_shopping pom_online = new POM_Online_shopping(driver);
		pom_online.click_signout();
		log.info("Signedout from the application for add cart" +TC_ID +" - "+ Order);
		tear_down();		
		log.info("Add Cart scenario completed" +TC_ID +" - "+ Order);
	}
	
	@Test(dataProvider = "DP_deletecart",dataProviderClass = DataProviderObjects.DP_CartScenario.class)
	public void deletecart(String TC_ID, String Order, String Uname, String Pwd, String Search_Item, String Quantity, String expected ) throws Exception
		{
			//SoftAssert assert1 = new SoftAssert();
			log.info("Intializing browser for Cart scenario : add cart"+ TC_ID+ "-"+ Order);
			Initialize_browser();
			log.info("Intializing browser for Cart scenario :add cart has completed"+ TC_ID+ "-"+ Order);
			POM_LoginScenario pom_login = new POM_LoginScenario(driver);
			pom_login.execute_common_methods_log(Uname, Pwd);
			if (!driver.getTitle().equalsIgnoreCase("Buy Books Online | Online Bookstore India | Online Book Shopping | Free Shipping Across India"))
			{
				log.error("The user has failed to login, the page the user currently is " +driver.getTitle() +" - "+TC_ID +" - "+ Order);
				tear_down();
				Assert.fail();
				
			}
			POM_HomePage pom_home = new POM_HomePage(driver);
			pom_home.execute_comm_methods(Search_Item);
			//System.out.println("Item found");
			pom_home.click_img();
			//System.out.println("item selected");
			log.info("The book searched has been found and selected " +TC_ID +" - "+ Order);
			POM_Buynow pom_buy = new POM_Buynow(driver);
			pom_buy.click_buynow();
			log.info("Buy now functionality completed " +TC_ID +" - "+ Order);
			//System.out.println("buynow successfull");
			POM_shopping pom_shopping = new POM_shopping(driver);
			pom_shopping.click_deleteicon();
			log.info("deleted book from the cart" +TC_ID +" - "+ Order);
			String actual = pom_shopping.get_emptycartmsg();
			if(actual.equalsIgnoreCase(expected)){
				log.info("Validation PASSED for  Cart scenario : delete search  completed"+ TC_ID+ "-"+ Order);
			}
			else
			{
				log.error("The actual and expected doesn't match for  delete cart"+ TC_ID+ "-"+ Order );
				tear_down();	
				Assert.fail();
						
			}
		//	assert1.assertEquals(actual, expected);
			tear_down();
			//assert1.assertAll();
		}
	

}
