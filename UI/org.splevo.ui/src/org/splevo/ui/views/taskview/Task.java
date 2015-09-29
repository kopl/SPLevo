package org.splevo.ui.views.taskview;

/**
 * Class Task for encapsulating task-attributes
 */
public class Task {
	private final String description;
	private final String resource;
	private final String path;
	private final int location;

	/**
     * Constructor with parameters to initialize the membervariables
     *
     * @param newDescription
     *            contains the task description.
     * @param newResource
     *            contains the resource of the task.
     * @param newPath
     *            contains the path where the tasktag was found.
     * @param newLocation
     *            contains the line number of the found tasktag.
     */
	public Task(String newDescription, String newResource, String newPath, int newLocation) {
		this.description = newDescription;
		this.resource = newResource;
		this.path = newPath;
		this.location = newLocation;
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
