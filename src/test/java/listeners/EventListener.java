package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class EventListener implements ITestListener {


	public void onTestStart(ITestResult result) {	
		
		Reporter.log("******************************************", true);

		Reporter.log("---Test STARTED--->"+result.getInstanceName()+"."+result.getName(), true);
		
	}

	public void onTestSuccess(ITestResult result) {

		Reporter.log("---Test PASSED--->"+result.getInstanceName()+"."+result.getName(), true);
	}

	public void onTestFailure(ITestResult result) {

		Reporter.log("---Test FAILED--->"+result.getInstanceName()+"."+result.getName(), true);
	}

}
