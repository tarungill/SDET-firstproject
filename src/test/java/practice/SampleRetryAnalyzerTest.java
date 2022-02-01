package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.genericlib.BaseClassAnnotation;

public class SampleRetryAnalyzerTest extends BaseClassAnnotation {
	
	@Test(retryAnalyzer = com.crm.genericlib.ReTryImpClass.class)
	public void amazontest() {
		
		Assert.assertEquals("A", "B");
	}

}
