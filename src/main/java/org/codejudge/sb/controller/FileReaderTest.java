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
import java.util.concurrent.Callable;



public class FileReaderTest implements Callable<Map<String, Map<String, Integer>>> {

    String fileName;

    FileReaderTest(String fileName){
        this.fileName=fileName;
    }
    //@Override
    public Map<String, Map<String, Integer>>  call() throws Exception {
                           Map<String, Map<String, Integer>> outerMap=new TreeMap<>();
                                         Scanner sc = null;
									try { 
									//System.out.println(logFilesmain.get(0) +"-"+thcount);
											URL url = new URL(fileName);
											
											//System.out.println(logFilesmain.get(thcount));
											//inputStream = new FileInputStream(path);
											sc = new Scanner(url.openStream());
											while (sc.hasNextLine())
												{
												String line = sc.nextLine();
												 String[] arrOfStr =line.split(" ");
											 								 
												try
												{												
											  String datefinal=Datefunction(arrOfStr[1]);
												 //System.out.println("Post date");
												if(outerMap.containsKey(datefinal))
												   {
												//	System.out.println("IF one"); 
													Map<String,Integer> innerMap = new TreeMap<String,Integer>(); 
                                                     
													innerMap=outerMap.get(datefinal);
													if(innerMap.containsKey(arrOfStr[2]))
													{
													//	System.out.println("two");
	                							    innerMap.put(arrOfStr[2],innerMap.get(arrOfStr[2])+1);
													}
													else{
														innerMap.put(arrOfStr[2],1);
													}
													outerMap.put(datefinal,innerMap);
												    }
												else{
												Map<String,Integer> innerMap1 = new TreeMap<String,Integer>();    
													innerMap1.put(arrOfStr[2],1);
													outerMap.put(datefinal,innerMap1);
													
											         }
											   }
                                                catch(Exception e)
                                                {   //System.out.println("1");
													System.out.println(e.toString());
												}
										     //System.out.println("Post hasmap");
											
										   }
										// note that Scanner suppresses exceptions
										if (sc.ioException() != null) 
										{
											throw sc.ioException();
										}
									  }
									catch(Exception e)
                                                {   System.out.println("2");
													System.out.println(e.toString());
												}
									finally {
										// System.out.println("Finally");
												if (sc != null) {
													sc.close();
												}
											}			 						   
								

        return outerMap;
    }

 public String Datefunction(String ipDate)
   {
	                                           try{
												   
	                                            long timestampMilliseconds = Long.parseLong(ipDate);//*1000L;
												SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm",Locale.US);
												simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
												String stringDate = simpleDateFormat.format(new Date(timestampMilliseconds));
												Date check=simpleDateFormat.parse(stringDate);
												Calendar cal = Calendar.getInstance();
												cal.setTime(check);//new Date(timestampMilliseconds));//simpleDateFormat.parse(stringDate));// all done
											 	int currentMinutes = cal.get(Calendar.MINUTE);
												int correctionToLowestQuarterHour = currentMinutes % 15;
												cal.set(Calendar.MINUTE, currentMinutes + (15-correctionToLowestQuarterHour));
                                                cal.set(Calendar.SECOND, 0);
                                                cal.set(Calendar.MILLISECOND, 0);

                                                Calendar calend = Calendar.getInstance();
												calend.setTime(check);//new Date(timestampMilliseconds));//simpleDateFormat.parse(stringDate));// all done
											 	int currentMinutes1 = calend.get(Calendar.MINUTE);
												int correctionToLowestQuarterHour1 = currentMinutes % 15;
												calend.set(Calendar.MINUTE, currentMinutes1 - correctionToLowestQuarterHour1);
                                                calend.set(Calendar.SECOND, 0);
                                                calend.set(Calendar.MILLISECOND, 0);												
												String datefinal=simpleDateFormat.format(calend.getTime())+"-"+simpleDateFormat.format(cal.getTime());
												return datefinal;
											   }
											   catch (Exception e)
											   {
												   return "Error";
											   }
											//	System.out.println(simpleDateFormat.format(cal.getTime())+"-"+simpleDateFormat.format(calend.getTime())); 
												
												//outerMap.put(datefinal,outerMap.get(datefinal));
												
   }



}