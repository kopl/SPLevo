package org.splevo.ui.listeners;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Scheduling rule able to join potentially parallel jobs before scheduling
 * the managed job.
 * 
 */
public class JoinJobSchedulingRule implements ISchedulingRule {

	/** Identifies whether the currently scheduled job is a join rule. */
	private boolean isJoinInstance = false;
	
	/** The job to join all other jobs for. */
	private Job scheduledJob = null;
	
	/**
	 * Constructor requiring the scheduled job and the setting 
	 * whether it should managed the join. 
	 * @param scheduledJob The scheduled job.
	 * @param isJoinInstance Whether it is responsible to join or not.
	 */
	public JoinJobSchedulingRule(Job scheduledJob, boolean isJoinInstance) {
		this.scheduledJob = scheduledJob;
		this.isJoinInstance = isJoinInstance;
	}

	/**
	 * This scheduling rule is conflicting if it is set as join instance
	 * and the other rule is not an join job scheduling rule also set as
	 * join instance and joining the same job.
	 * 
	 * @return whether this job is conflicting or not.
	 */
	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if(rule == this){
			return true;
		} else if(!isJoinInstance || rule == null){
			return false;
		} else if(rule instanceof JoinJobSchedulingRule){
			JoinJobSchedulingRule joinJobRule = (JoinJobSchedulingRule) rule;
			if(!joinJobRule.isJoinInstance()){
				return false;
			} else if(joinJobRule.getScheduledJob() == this.scheduledJob){
				return false;
			}
		}
		return true;
	}

	/**
	 * @return the isJoinInstance
	 */
	public boolean isJoinInstance() {
		return isJoinInstance;
	}

	/**
	 * @return the scheduledJob
	 */
	public Job getScheduledJob() {
		return scheduledJob;
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		return (rule == this);
	}
}
