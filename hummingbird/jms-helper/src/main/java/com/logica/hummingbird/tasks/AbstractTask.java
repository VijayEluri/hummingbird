package com.logica.hummingbird.tasks;

import java.io.Serializable;

import org.apache.camel.Message;

import com.logica.hummingbird.interfaces.ITask;


public abstract class AbstractTask implements ITask, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected long deltaTime = 0;
	
	public AbstractTask(long deltaTime) {
		this.deltaTime = deltaTime;
	}
	
	public long deltaTime() {
		return deltaTime;
	}
	
	@Override
	public void configure(Message in) {
		/** Do NULL*/		
	}
}
