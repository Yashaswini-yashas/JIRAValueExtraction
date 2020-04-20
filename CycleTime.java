import static io.restassured.RestAssured.given;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CycleTime {
	@Test
	public void cycleTime() throws ParseException {
		RestAssured.baseURI="http://localhost:8090/";
		Response res=given().auth().preemptive().basic("YashaswiniS", "jira1@345").
				when().
			    get("/rest/api/2/issue/10206?expand=changelog").
			    then().assertThat().
			    and().
			    extract().response();
		     String response=res.asString();	
		     System.out.println(response);
		     JsonPath js = new JsonPath(response);
		   //Prints the time issue was assigned to an Assignee
    	     //or
    	     // prints the time when the status is in progress
    	     String progress = js.get("changelog.histories[0].created");
    	     System.out.println("The time issue got assigned as In Progress:" +" "+progress);
    	     
    	     String substr1 = progress.substring(11, 19);
    	     String datestring1 = progress.substring(0, 10);
   	         String End = datestring1 + " " + substr1 ;
    	     System.out.println(End);
    	     
    	     // Prints the date and time of Ticket Resolution		    	     		    	     
    	     String resolutionDate = js.get("fields.resolutiondate");
    	     System.out.println("Issue Resolution Date and Time:" +" "+resolutionDate);
    	     String substr = resolutionDate.substring(11, 19);		    	  
    	     String datestring = resolutionDate.substring(0, 10);    	  
    	     String Start = datestring + " " + substr ;
    	     System.out.println(Start);
    	     
    	     String dateStart = Start;
    			String dateStop = End;
    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    			Date d1 = null;
    			Date d2 = null;

    				d1 = format.parse(dateStart);
    				d2 = format.parse(dateStop);

    				//in milliseconds
    				long diff = d1.getTime() - d2.getTime();
    				long diffSeconds = diff / 1000 % 60;
    				long diffMinutes = diff / (60 * 1000) % 60;
    				long diffHours = diff / (60 * 60 * 1000) % 24;
    				long diffDays = diff / (24 * 60 * 60 * 1000);
    				System.out.print("The Cycle time is:" + " ");
    				System.out.print(diffDays + " days, ");
    				System.out.print(diffHours + " hours, ");
    				System.out.print(diffMinutes + " minutes, ");
    				System.out.print(diffSeconds + " seconds." + "\n");
	}
}
