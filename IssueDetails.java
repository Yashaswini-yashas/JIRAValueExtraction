import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class IssueDetails {
	@Test
public void issueDetails() {
	RestAssured.baseURI="http://localhost:8090/";
	Response res=given().auth().preemptive().basic("username", "password").
			when().
		    get("/rest/api/2/search").
		    then().assertThat().
		    and().
		    extract().response();
	     String response=res.asString();	
	     System.out.println(response);
	     JsonPath js = new JsonPath(response);
         ArrayList IssueKey = js.get("issues.key");
	     System.out.println("The Issue Keys are:" +" "+IssueKey);
	     ArrayList Issue_id = js.get("issues.id");
	     System.out.println("The Issue Id's are:" +" "+Issue_id);
}
}
