package org.codejudge.sb.controller;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.awt.List;

public class logclass {
    public String timest;
	public ArrayList<String> logs;
	
	
    public void setlogs(ArrayList<String> logs) {
        this.logs = logs;
    }
  
    public ArrayList<String> getlogs() {
        return logs;
    }
	
	 public void timest(String timest) {
        this.timest = timest;
    }
  
    public String gettimest() {
        return timest;
    }
    
/* 	public String toString()
	{
		return logFiles[0];
	} */
}


 /* public class Data 
    { 
        // global variables of the class 
        int roll; 
        String name; 
        
  
        // constructor has type of data that is required 
        Data(int roll, String name) 
        { 
            // initialize the input variable from main 
            // function to the global variable of the class 
            this.roll = roll; 
            this.name = name; 
            this.marks = marks; 
            this.phone = phone; 
        } 
    }  */