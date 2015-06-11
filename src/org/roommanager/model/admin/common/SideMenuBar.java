package org.roommanager.model.admin.common;

import org.openqa.selenium.By;

public enum SideMenuBar {

	EMAILSERVERLINK(By.linkText("Email Servers")),
	IMPERSONATIONLINK(By.linkText("Impersonation")),
	CONFERENCELINK(By.linkText("Conference Rooms")),
	RESOURCESLINK(By.linkText("Resources")),
	ISSUESLINK(By.linkText("Issues")),
	TABLETSLINK(By.linkText("Tablets"));
	
	public By value;
	
	private SideMenuBar(By locator){
		this.value = locator;
	}
}
