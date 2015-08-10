package org.splevo.ui.views.taskview;

public class Task {
	private String description = null;
	private String resource = null;
	private String path = null;
	private int location = 0;

	public Task(String Description, String Resource, String Path, int Location) {
		this.description = Description;
		this.resource = Resource;
		this.path = Path;
		this.location = Location;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getResource() {
		return this.resource;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public int getLocation() {
		return this.location;
	}
}
