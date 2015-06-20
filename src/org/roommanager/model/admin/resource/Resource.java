package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum Resource {
	ADD_RESOURCE_BUTTON(By.xpath("//div/div/button")),
	REMOVE_RESOURCE_BUTTON(By.id("btnRemove")),
	NEXT_PAGE_BUTTON(By.xpath("//div[@id='resourcesGrid']/div[3]/div/div[2]/div[2]/button[3]")),
	NEXT_PAGE_INPUT(By.xpath("//input[@type='number']")),
	RESOURCES_TABLE_NUMBER_OF_PAGES(By.xpath("//*[@id='resourcesGrid']/div[3]/div/div[2]/div[2]/span")),
	SEARCH_RESOURCE_TEXT_FIELD(By.xpath("//input[@type='text']")),
	RESOURCES_LIST(By.xpath("//div[@id='resourcesGrid']/div[2]/div")),
	RESOURCE_TABLE_ITEM(By.xpath("div[3]/div[2]/div/span")),
	DIV_ELEMENT(By.xpath("div"));
	
	public By value;
	
	private Resource(By locator){
		this.value = locator;
	}
}
