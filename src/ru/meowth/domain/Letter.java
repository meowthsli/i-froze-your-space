package ru.meowth.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PrimaryKey;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author meowth
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Letter {

	/**
	 * Letter constructor
	 * @param text Letter text
	 * @param email Letter recipient
	 * @param delivery 
	 * @param span
	 */
	public Letter(String body, String email, DeliverySpan span) {
		this.body = body;
		this.email = email;
		this.whenToDeliver = whenToShoot(span);
	}
	
	public Letter makeClone() {
		Letter l = new Letter(getBody(), getEmail(), DeliverySpan.VerySoon);
		l.whenToDeliver = l.getWhenToDeliver();
		
		return l;
	}
	
	/**
	 * Message identity
	 */
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
	@PrimaryKey
	private String id;
	
	public String getId() {
		return id;
	}
	
	/**
	 * Message body
	 */
	@Persistent
	private String body;
	
	public String getBody() {
		return body;
	}
	
	/**
	 * Message recipient
	 */
	@Persistent
	private String email;
	
	public String getEmail(){
		return email;
	}
	
	/**
	 * When message should be sent
	 */
	@Persistent
	private Date whenToDeliver;
	
	public Date getWhenToDeliver() {
		return whenToDeliver;
	}
	
	@SuppressWarnings("deprecation")
	private static Date whenToShoot(DeliverySpan span) {		
	
		Random r = new Random();		
		final float percents = (float)(r.nextInt(20) + 80);
		
		Calendar c = Calendar.getInstance();		
		switch(span){
		case VerySoon:
			c.add(Calendar.DAY_OF_MONTH, (int)(percents * 7f));
			break;
		case Soon:
			c.add(Calendar.DAY_OF_MONTH, (int)(percents * 30f));
			break;
		case RelativelySoon:
			c.add(Calendar.DAY_OF_MONTH,(int)(percents * 3f * 30f));
			break;			
		case NotSoSoon:
			c.add(Calendar.DAY_OF_MONTH, (int)(percents * 6f * 30f));
			break;
		case AlmostNever:
			c.add(Calendar.DAY_OF_MONTH, (int)(percents * 12f * 30f));
			break;
		}
		
		return new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 
				c.get(Calendar.DAY_OF_MONTH));
	}
}
