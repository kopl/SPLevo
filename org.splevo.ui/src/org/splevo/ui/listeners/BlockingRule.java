package org.splevo.ui.listeners;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class BlockingRule implements ISchedulingRule {

	private boolean blocking = false;
	
	public BlockingRule() {
		// TODO Auto-generated constructor stub
	}
	
	public BlockingRule(boolean blocking) {
		this.blocking = blocking;
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if(this == rule || rule == null){
			return true;
		} else  if(rule instanceof BlockingRule){
			return blocking;
		}
		return false;
	}

}
