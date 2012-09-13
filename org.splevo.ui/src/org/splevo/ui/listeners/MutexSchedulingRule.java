package org.splevo.ui.listeners;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * Ensures parallel execution of the scheduled jobs.
 */
public class MutexSchedulingRule implements ISchedulingRule {

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		return rule == this;
	}

}
