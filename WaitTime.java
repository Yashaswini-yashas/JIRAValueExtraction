import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WaitTime {
@Test
public void waitTime() throws ParseException {
	RestAssured.baseURI="http://localhost:8090/";
	Response res=given().auth().preemptive().basic("YashaswiniS", "jira1@345").
			when().
		    get("/rest/api/2/issue/10201?expand=changelog").
		    then().assertThat().
		    and().
		    extract().response();
	     String response=res.asString();	
	     System.out.println(response);
	     JsonPath js = new JsonPath(response);
	 	
         // Prints the date and time of Ticket Creation		             
   	     String created = js.get("fields.created");
   	     System.out.println("Issue Creation Date and Time:" +" "+created);
   	     String substr = created.substring(11, 19);
   	     String datestring = created.substring(0, 10);
   	     String Start = datestring + " " + substr ;
   	     System.out.println(Start);
            
            //Prints the time issue was assigned to an Assignee
   	     //or
   	     // prints the time when the status is in progress
   	     String progress = js.get("changelog.histories[0].created");
   	     System.out.println("The time issue got assigned as In Progress:" +" "+progress);
   	     
   	     String substr1 = progress.substring(11, 19);
   	     String datestring1 = progress.substring(0, 10);
  	         String End = datestring1 + " " + substr1 ;
   	     System.out.println(End);
   	     
   	     
   	     String dateStart = Start;
   			String dateStop = End;
   			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   			Date d1 = null;
   			Date d2 = null;

   				d1 = format.parse(dateStart);
   				d2 = format.parse(dateStop);

   				//in milliseconds
   				long diff = d2.getTime() - d1.getTime();
   				long diffSeconds = diff / 1000 % 60;
   				long diffMinutes = diff / (60 * 1000) % 60;
   				long diffHours = diff / (60 * 60 * 1000) % 24;
   				long diffDays = diff / (24 * 60 * 60 * 1000);
   				System.out.print("The Wait time is:" + " ");
   				System.out.print(diffDays + " days, ");
   				System.out.print(diffHours + " hours, ");
   				System.out.print(diffMinutes + " minutes, ");
   				System.out.print(diffSeconds + " seconds."+"\n");
}
}
