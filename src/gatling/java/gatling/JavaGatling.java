package gatling;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class JavaGatling extends Simulation{

HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080").acceptHeader("application/json");
	
	ScenarioBuilder scn =  scenario("Test using GAtling Java")
		    .exec(
		    	      http("Get Employee By Id ")
		    	        .get("/api/employee/retrieval/1")
		    	        .check(status().is(200))
		    	    )
		    	    .pause(5);
	
	ScenarioBuilder scn2 =  scenario("Test using GAtling Java for All Employee")
		    .exec(
		    	      http("Get All Employee")
		    	        .get("/api/employee/all")
		    	        .check(status().is(200))
		    	    )
		    	    .pause(2);
	{
	    setUp(
	      scn.injectOpen(
	        atOnceUsers(1), // Start with 1 users at once
	        rampUsers(5).during(10) // Gradually add 5 users over 10 seconds
	      ),
	      
	      scn2.injectOpen(
	  	        atOnceUsers(1), // Start with 1 users at once
	  	        rampUsers(10).during(5) // Gradually add 10 users over 5 seconds
	  	      )
	    ).protocols(httpProtocol);
	  }
	


}
