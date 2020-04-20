import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProjectDetails {
	@Test
	public void projectDetails() {
		RestAssured.baseURI="http://localhost:8090/";
		Response res=given().auth().preemptive().basic("username", "password").
				when().
			    get("/rest/api/2/issue/createmeta").
			    then().assertThat().
			    and().
			    extract().response();
		     String response=res.asString();	
		     System.out.println(response);
		     JsonPath js = new JsonPath(response);
		     ArrayList projects = js.get("projects.name");
		     System.out.println("The Project names are:" +" "+projects);
		     ArrayList project_id = js.get("projects.id");
		     System.out.println("The Project ID'S are:" +" " +project_id);
		     ArrayList key = js.get("projects.key");
		     System.out.println("The Project Keys are:" + " " +key+"\n");
	}
}
