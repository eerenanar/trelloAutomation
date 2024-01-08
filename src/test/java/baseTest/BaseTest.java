package baseTest;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utils.ExtentReportListener;
import utils.FileandEnv;

@Listeners(ExtentReportListener.class)
public class BaseTest extends ExtentReportListener {


    @BeforeClass
    public void baseTest(){

        RestAssured.baseURI= FileandEnv.envAndFile().get("BaseUrl");

    }
}
