package org.roommanager.model.tablet.scheduler;

import org.openqa.selenium.By;

public enum Scheduler {
	ORGANIZERTEXTFIELD(By.id("txtOrganizer")),
	SUBJECTTEXTFIELD(By.id("txtSubject")),
	CREATEBUTTON(By.xpath("//button[4]"));
	
	public By value;
	
	private Scheduler(By locator){
		this.value = locator;
	}
}
