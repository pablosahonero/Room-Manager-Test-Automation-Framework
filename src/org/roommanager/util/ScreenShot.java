package org.roommanager.util;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.net.*;

public class ScreenShot extends TestListenerAdapter {

	//Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult tr) {
		takeScreenShot();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
	//ScreenShot();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
	//ScreenShot();
	}

	private void takeScreenShot() {
	try {

	String NewFileNamePath;

	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension scrnsize = toolkit.getScreenSize();

	File directory = new File (".");
	
	DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
	Date date = new Date();

	InetAddress ownIP=InetAddress.getLocalHost();
	NewFileNamePath = directory.getCanonicalPath()+ "\\screenshots\\"+ dateFormat.format(date)+"_"+ownIP.getHostAddress()+ ".png";

	Robot robot = new Robot();
	BufferedImage bi=robot.createScreenCapture(new Rectangle(scrnsize));
	ImageIO.write(bi, "png", new File(NewFileNamePath));
	} 
	catch (AWTException e) {
	e.printStackTrace();
	} 
	catch (IOException e) {
	e.printStackTrace();
	}
	}

}

