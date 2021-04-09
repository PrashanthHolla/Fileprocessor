package org.codejudge.sb.controller;
import org.codejudge.sb.controller.fileurl;
import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.awt.List;


public class Objectclass {
   
	public ArrayList<String> logFiles;
	public int parallelFileProcessingCount;
    public void setparallelFileProcessingCount(int parallelFileProcessingCount) {
        this.parallelFileProcessingCount = parallelFileProcessingCount;
    }
  
	public void setlogFiles(ArrayList<String> logFiles) {
        this.logFiles = logFiles;
    }
    public int getparallelFileProcessingCount() {
        return parallelFileProcessingCount;
    }
   
	 public ArrayList<String> getlogFiles() {
        return logFiles;
    }
	
	public String toString()
	{
		return logFiles.toString() + String.valueOf(parallelFileProcessingCount);
	}
}