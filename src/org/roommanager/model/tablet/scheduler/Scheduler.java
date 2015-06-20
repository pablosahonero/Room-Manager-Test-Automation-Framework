package org.roommanager.model.tablet.scheduler;

import org.openqa.selenium.By;

public enum Scheduler {
	ORGANIZER_TEXTFIELD(By.xpath("//*[@id='txtOrganizer']")),
	SUBJECT_TEXT_FIELD(By.xpath("//input[@id='txtSubject']")),
	ATTENDEES_TEXT_FIELD(By.xpath("(//input[@type='text'])[3]")),
	CREATE_BUTTON(By.xpath("//button[4]")),
	REMOVE_BUTTON(By.xpath("//button[2]")),
	UPDATE_BUTTON(By.xpath("//button[3]")),
	ATTENDEES_LIST(By.xpath("//rm-tag-input/ul")),
	ATTENDEES_LIST_ELEMENT(By.xpath("li")),
	TIME_LINE(By.xpath("//div[@id='timelinePanel']")),
	MEETINGS_IN_TIME_LINE(By.xpath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div")),
	MEETING_ELEMENT_TEXT(By.xpath("div/span")),
	DIV_ELEMENT(By.xpath("div")),
	SPAN_ELEMENT(By.xpath("span"));
	
	public By value;
	
	private Scheduler(By locator){
		this.value = locator;
	}
}
