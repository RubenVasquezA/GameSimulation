package ukb;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Simulation {
	
	 /**
	    * get a value by min and max and return a random number 
	    *
	    * @param  min   min bound
	    * @param  max	max bound
	    * @return       random number between min and max
	*/
	static int getRandomNumber(int min, int max) {
		return (int)Math.floor(Math.random()*(max-min+1)+min);
		
	}
	
	/**
	    * get a list of entries of every shoot by the number of cowboys
	    * return an array of the entries objects 
	    *
	    * @param  number_cowboys   number of cowboys
	    * @return       list of entry objects
	*/
	static ArrayList<Report> runSimulationOfTheGame(int number_cowboys) {
	   
		// We define a list of cowboys objects 	
		List<Cowboy> list_cowboys=new ArrayList<Cowboy>();  
		
		// We define a object from the class report
		List<Report> report = new ArrayList<Report>();
		
		boolean start; // this boolean variable is to start the shootout 
		int cowboy_index, // cowboy index of the list
			target_cowboy_index, // target of the cowboy index
			h_p_out, // health points that will be taken out 
			count; // count the number of iteration that shootout takes.
		    
	    count = 0;
		start = true;

		// To generate a number between 1-number_cowboys-1 in order to 
		// get a random cowboy index
		
		cowboy_index = getRandomNumber(0,number_cowboys - 1); 
		
		for (int i = 0; i < number_cowboys; i++) {
			Cowboy c = new Cowboy(i,10); // create the object cowboy
			list_cowboys.add(c); // list of cowboys according to 
			 					  // indexs
		}  
		
		while(start==true) {
			
			Cowboy cowboyObject = list_cowboys.get(cowboy_index); //we take the activate cowboy 
			
			 // we get the direction of shoot according to health points of 
			 // the active cowboy.
			
			 if(cowboyObject.getHealthPoints() % 2 == 0) {
				 
				 if(cowboy_index == list_cowboys.size() - 1) {
					 target_cowboy_index = 0; // cowboy n-1 -> 0 
				 }else {
					 target_cowboy_index = cowboy_index + 1; //normal case
				 }
			 }else {
				 
				 if(cowboy_index == 0) {
					 target_cowboy_index = list_cowboys.size() - 1; // e.g cowboy 0 -> cowboy number_cowboys-1
				 }else {
					 target_cowboy_index = cowboy_index - 1;  // normal case
				 }
			 }
			 
			 // We get the object of the target cowboy
			 Cowboy cowboyTargetObject = list_cowboys.get(target_cowboy_index); 
			 
			 // To generate a number between 1-5
			 h_p_out = getRandomNumber(1,5);
			 
			 // To set the new values of the list
			 cowboyTargetObject.setHealthPoints(cowboyTargetObject.getHealthPoints()-h_p_out);
			 
			 // create a report object
			 Report r = new Report(cowboyObject.getIndex(), cowboyTargetObject.getIndex(), h_p_out, cowboyTargetObject.getHealthPoints());
			 report.add(r);
			 
			 // if the h_p of the target cowboy is less or equal than 0.
			 if (cowboyTargetObject.getHealthPoints()<=0) {
					
				// we remove the object from the list. We close the circle.
				list_cowboys.remove(cowboyTargetObject); 
				
				// we get the cowboy index.
				cowboy_index = list_cowboys.indexOf(cowboyObject);
				
			 }else {
				 
				 // we get the cowboy index (the cowboy target turn).
				 cowboy_index = list_cowboys.indexOf(cowboyTargetObject);
			 }
			  // Check if the size of the list of cowboy objects is 1.   
			  if (list_cowboys.size()==1) {
			    	start = false;
			  }
			  
			  count = count + 1;
			  		 
		 }
		return (ArrayList<Report>) report;
		
	}
	
	public static void main(String[] args) {
	 // In this part we are going to simulate the shootout, generate the JSON
	 // do get MD5CheckSum.
		
		JSONArray listOfEntries = new JSONArray();
		JSONObject results = new JSONObject();
		MD5Checksum md5 = null;
		
		int number_cowboys = 100; // we define 5 as the number of cowboys.
		
		// to get the report of the simulation
		ArrayList<Report> report_of_simulation = runSimulationOfTheGame(number_cowboys);
		
		// we print the roport in the console. 
		// we save the report in a JSON file.
		// The json format report has the shape of: 
		/*
		 * {"results": [
		 * 
		 * { "whoFired": the index of the current cowboy.
		 * 	 "whoWasHit": the index of the target cowboy.
		 * 	 "lhPoints": the lost health points.
		 * 	 "lhPointsTarget": the left health points of the target.
		 * }
		 * 
		 * ]}*/
		
		for(int i=0;i<report_of_simulation.size();i++) {
			
			JSONObject entry = new JSONObject();
			System.out.println("shoot number: "+i);
			System.out.println("Who fired: "+ report_of_simulation.get(i).getWhoFired());
			System.out.println("Who was hit: "+ report_of_simulation.get(i).getWasHit());
			System.out.println("Lost health points: "+ report_of_simulation.get(i).getHpLost());
			System.out.println("health points of the target has left: "+ report_of_simulation.get(i).getTargetLeft());
			System.out.println("================================");
			
			entry.put("whoFired", report_of_simulation.get(i).getWhoFired());
			entry.put("whoWasHit", report_of_simulation.get(i).getWasHit());
			entry.put("lhPoints", report_of_simulation.get(i).getHpLost());
			entry.put("lhPointsTarget", report_of_simulation.get(i).getTargetLeft());
			
			listOfEntries.add(entry);
		}
		results.put("results", listOfEntries);
		 try {
	            // Writing to a file
	            File file=new File("Report.json");
	            file.createNewFile();
	            FileWriter fileWriter = new FileWriter(file, Charset.forName("UTF8"));
	            System.out.println("Writing JSON object to file :)");
	            System.out.println("-------------------------------");
	            fileWriter.write(results.toJSONString());
	            fileWriter.flush();
	            fileWriter.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 // Here we print the md5CheckSum in the console once the report is out.
		 try {
			  
			   System.out.println("The md5CheckSum is:");
	           System.out.println(md5.getMD5Checksum("Report.json"));
	           // output :
	           //  0bb2827c5eacf570b6064e24e0e6653b
	           // ref :
	           //  http://www.apache.org/dist/
	           //          tomcat/tomcat-5/v5.5.17/bin
	           //              /apache-tomcat-5.5.17.exe.MD5
	           //  0bb2827c5eacf570b6064e24e0e6653b *apache-tomcat-5.5.17.exe
	       }
	       catch (Exception e) {
	           e.printStackTrace();
	       }
		
	}

}
