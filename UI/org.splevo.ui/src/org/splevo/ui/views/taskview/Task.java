package org.splevo.ui.views.taskview;

/**
 * Class Task for encapsulating task-attributes
 */
public class Task {
	private String description = null;
	private String resource = null;
	private String path = null;
	private int location = 0;

	/**
     * Constructor with parameters to initialize the membervariables
     *
     * @param description
     *            contains the task description.
     * @param resource
     *            contains the resource of the task.
     * @param path
     *            contains the path where the tasktag was found.
     * @param location
     *            contains the line number of the found tasktag.
     */
	public Task(String description, String resource, String path, int location) {
		this.description = description;
		this.resource = resource;
		this.path = path;
		this.location = location;
	}
	
	/**
     * Gets the task description.
     *
     * @return the description
     */
	public String getDescription() {
		return this.description;
	}
	
	/**
     * Gets the task resource.
     *
     * @return the resource
     */
	public String getResource() {
		return this.resource;
	}
	
	/**
     * Gets the task path.
     *
     * @return the path
     */
	public String getPath() {
		return this.path;
	}
	
	/**
     * Gets the task location.
     *
     * @return the location
     */
	public int getLocation() {
		return this.location;
	}
}
