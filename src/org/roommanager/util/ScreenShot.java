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

public class ScreenShot extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult testResult) {
		takeScreenShot(testResult.getName());
	}

	private void takeScreenShot(String testName) {
	String fileDirectory = ".";
	String dateFormatAsString = "dd_MMM_yyyy__hh_mm_ssaa";
	String reportNgProperty = "org.uncommons.reportng.escape-output";
	String filePath = "\\screenshots\\";
	String imageFormat = "png";
	String failedTestImageLinkTag = "<a href=\"[filePath]"
            + "\"><img src=\"file:///[filePath]"
            + "\" alt=\"\"" + "height='100' width='100'/> <br />";
	
	try {
		System.setProperty(reportNgProperty, "false");
		String fileNamePath;
	
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screensize = toolkit.getScreenSize();
	
		File directory = new File (fileDirectory);
		
		DateFormat dateFormat = new SimpleDateFormat(dateFormatAsString);
		Date date = new Date();
	
		fileNamePath = directory.getCanonicalPath()+ 
			filePath + "/" + testName +
			"-" + dateFormat.format(date)+ 
			"." +imageFormat;
	
		Robot robot = new Robot();
		BufferedImage bi=robot.createScreenCapture(new Rectangle(screensize));
		ImageIO.write(bi, imageFormat, new File(fileNamePath));
		failedTestImageLinkTag = failedTestImageLinkTag
			.replace("[filePath]", fileNamePath);
		Reporter.log(failedTestImageLinkTag); 
	} 
	catch (AWTException e) {
	e.printStackTrace();
	} 
	catch (IOException e) {
	e.printStackTrace();
	}
	}

}

