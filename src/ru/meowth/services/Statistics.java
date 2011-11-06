package ru.meowth.services;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Statistics {
	
	@SuppressWarnings("unused")
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	@PrimaryKey
	private String id;
	
	@Persistent
	private int lettersSent;
	
	@Persistent
	private int lettersQueued;
	
	public int getLettersSent() {
		return lettersSent;
	}
	
	public synchronized void setLettersSent(int count) {
		lettersSent = count;
	}
	
	public synchronized void addLettersSent(int count) {
		setLettersSent(getLettersSent() + count);
		setLettersQueued(getLettersQueued() - count);
	}	
	
	public int getLettersQueued() {
		return lettersQueued;
	}
	
	public synchronized void setLettersQueued(int count) {
		lettersQueued = count;
	}
		
	public synchronized void incrementLettersQueued() {
		setLettersQueued(getLettersQueued()+1);
	}
}
