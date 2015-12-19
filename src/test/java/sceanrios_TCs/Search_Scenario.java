package sceanrios_TCs;

import generic_library.Base_Class;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.Test;
import PageObjectRepository.POM_HomePage;

public class Search_Scenario extends Base_Class {
	static Logger log = Logger.getLogger(Search_Scenario.class);
	@Test(dataProvider = "DP_invalid_search",dataProviderClass = DataProviderObjects.DP_SearchScenario.class)
	public void invalidSearch(String TC_ID, String Order, String Search_Item, String expected  ) throws Exception
	{
		//SoftAssert assert1 = new SoftAssert();
		log.info("Intializing browser for Search scenario : invalid search"+ TC_ID+ "-"+ Order);
		Initialize_browser();
		log.info("Intializing browser for Search scenario : invalid search has completed"+ TC_ID+ "-"+ Order);
		POM_HomePage pom_invalid = new POM_HomePage(driver);
		pom_invalid.execute_comm_methods(Search_Item);
		log.info("Searching books has been complete for  Search scenario : invalid search "+ TC_ID+ "-"+ Order);
		String actual = pom_invalid.get_invalidmsg();
		//assert1.assertEquals(actual, expected);
		if(actual.equalsIgnoreCase(expected)){
			log.info("Validation PASSED for  Search scenario : invalid search  completed"+ TC_ID+ "-"+ Order);
		}
		else
		{
			log.error("The actual and expected doesn't match for invalid search"+ TC_ID+ "-"+ Order );
			tear_down();
			Assert.fail();
						
		}
		System.out.println(actual+ "  "+ expected);
		log.info("Validation for  Search scenario : invalid search  completed"+ TC_ID+ "-"+ Order); 
		
		tear_down();
		//assert1.assertAll();
	}
	
	@Test(dataProvider = "DP_valid_search",dataProviderClass = DataProviderObjects.DP_SearchScenario.class)
	public void avalidSearch(String TC_ID, String Order, String Search_Item, String expected ) throws Exception
	{		
		//SoftAssert assert1 = new SoftAssert();
		log.info("Intializing browser for Search scenario : valid search"+ TC_ID+ "-"+ Order);
		Initialize_browser();
		log.info("Intializing browser for Search scenario : valid search has completed"+ TC_ID+ "-"+ Order);
		POM_HomePage pom_valid = new POM_HomePage(driver);
		pom_valid.execute_comm_methods(Search_Item);
		log.info("Searching books has been complete for  Search scenario : valid search "+ TC_ID+ "-"+ Order);
		String actual = pom_valid.get_validmsg();
		//assert1.assertEquals(actual, expected.replace(".0", ""));
		if(actual.equalsIgnoreCase(expected)){
			log.info("Validation PASSED for  Search scenario : valid search  completed"+ TC_ID+ "-"+ Order);
		}
		else
		{
			log.error("The actual and expected doesn't match for valid search"+ TC_ID+ "-"+ Order );
			tear_down();	
			Assert.fail();
					
		}
		//System.out.println(actual+ "  "+ expected.replace(".0", ""));		
		tear_down();
		//assert1.assertAll();
	}

}
