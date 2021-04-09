package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.codejudge.sb.controller.Objectclass;
import org.codejudge.sb.controller.FileReaderTest;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.net.URI;
import java.io.File;  // Import the File class
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.*;
import java.text.SimpleDateFormat;
import org.codejudge.sb.controller.logclass;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
import com.google.gson.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.*;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import org.json.simple.parser.*; 

@RestController
@RequestMapping
public class AppController  {
     
	 String finalcheck="";
	/*  public static int thcount;
	 public static  int numberoffiles;
	 public static   Map<String, Map<String, Integer>> outerMap;// = new TreeMap<>();
		public static ArrayList<String> logFilesmain; */
    @ApiOperation("This is the hello world api")
    @GetMapping("/")
    public String hello() {
        return "Hello World!!";
    }

     @PostMapping(path= "/api/process-logs", consumes = "application/json", produces = "application/json")
      public ResponseEntity<Object> addvariable( @RequestBody Objectclass object) throws Exception

	  {                                

                                        if(object.parallelFileProcessingCount>0 && object.logFiles.size()>0)
                                        {
										List<String>  logFilesmain= new ArrayList<String>(); 
	                                     logFilesmain=object.getlogFiles();
										int numberoffiles=object.parallelFileProcessingCount;//number of threads
									    Map<String, Map<String, Integer>>  outerMap = new TreeMap<>();
	                                     int n = object.logFiles.size(); 
										 ExecutorService executorService = Executors.newFixedThreadPool(numberoffiles);
										 
										  List<Future< Map<String, Map<String, Integer>>>> result = new ArrayList<>();
                                          Map<String, Map<String, Integer>> finalResult = new TreeMap<>(); 
										          List<FileReaderTest> list1 = new ArrayList<>();

										 for(int i=0;i<n;i++){
											 
												list1.add(new FileReaderTest(logFilesmain.get(i)));
											}
																			
										 //submitting task with callable
											   for(int i=0;i<list1.size();i++){
													result.add(executorService.submit(list1.get(i)));
											   }
										   
										  //calculating result
											   for(int i=0;i<result.size();i++){
												   Map<String, Map<String, Integer>> temp = result.get(i).get();
												   
												   temp.forEach(
														   (k,v)->{
															   if(finalResult.containsKey(k)){
															 Map<String, Integer> tempinner =finalResult.get(k);
																   v.forEach
																   ((a,b)->{
																	   if(tempinner.containsKey(a))
																	   {
																		   tempinner.put(a,tempinner.get(a)+b);
																	   }
																	   else
																	   {
																		   tempinner.put(a,b);
																	   }
																   }
																   );
																   finalResult.put(k,tempinner);
															   }else{
																   finalResult.put(k,v);
															   }
														   }
												   );
											   }

										  
										  
									       JSONArray mJSONArray = new JSONArray();
                                     try
									 {
										 finalcheck="[";
									 									 
									  finalResult.forEach
									  ((z,x)->{
										 //mainentity.put("timestamp", z);
										   JSONObject mainentity = new JSONObject();
										    
									       JSONArray sJSONArray = new JSONArray();
										  Map<String, Integer> tempinner1 =finalResult.get(z);
										 Gson gson = new Gson(); 
										  tempinner1.forEach
																   ((a,b)->{
																	     JSONObject subentity = new JSONObject();
																	     subentity.put("exception", a); 
																	     subentity.put("count", b);
																		 sJSONArray.add(subentity);
																   }
																   );
										
											String tempjson = "{\"timestamp\":\""+z+"\",\"logs\":"+sJSONArray.toString()+"}";
									     	 System.out.println("tempjson"+tempjson);
									
										if(finalcheck.equals("["))
										{
											finalcheck=finalcheck+tempjson;
										}
										else
										{
											finalcheck=finalcheck+","+tempjson;
										}
										
										// mainentity.put("timestamp", z);
										 //mainentity.put("logs", sJSONArray);
										// try{
										 //JSONParser parser = new JSONParser();  
										/*  mainentity = (JSONObject) parser.parse(tempjson);  */ 
										 //mainentity = new JsonParser().parse(tempjson).getAsJsonObject();

										 //}
										 //catch(Exception e)
										 ///{
											// 	   System.out.println(e.toString());
										 //}
										 
									//	 mJSONArray.add(tempjson);
										 
										  
																			  
									  }
									  
									  );
									 }
                                     catch(Exception e)
									 {
										 System.out.println(e.toString());
									 }
                                       
												finalcheck=finalcheck+"]";
												//System.out.println("FinalJson - "+finalcheck);
											//	JSONParser parser = new JSONParser();  
										//JSONObject  mainentity = (JSONObject) parser.parse(finalcheck);  
										
												
										JSONObject finalJson = new JSONObject();
																	     finalJson.put("response", finalcheck); 
																		 //finalcheck
																	     	finalcheck="{\"response\":"+finalcheck+"}";
										System.out.println(finalcheck);
									 return new ResponseEntity<Object>(finalcheck, HttpStatus.OK);
									 }
									   else
									    {
											JSONObject entity = new JSONObject();
									        entity.put("status", "failure");
											entity.put("reason", "Parallel File Processing count must be greater than zero!");
											 return new ResponseEntity<Object>(entity, HttpStatus.BAD_REQUEST);
										}
	  }
 



}



