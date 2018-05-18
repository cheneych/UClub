package raymond.TestHomePage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Order {
	private LocalDate day;
	private LocalTime startTime;
	private LocalTime endTime;
	private String evtName;
	private String custName;
	private int id;
	//private String room;
	//private OrderStatus status;
	

	public Order() {
		
	}
	
	public Order(LocalDate day, LocalTime startTime, LocalTime endTime,String des,String room,OrderStatus status) {
		this.day=day;
		this.startTime=startTime;
		this.endTime=endTime;
		this.evtName=evtName;
//		this.room=room;
//		this.status=status;
	}
	
	
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

//	public LocalTime getStartTime() {
//		return startTime;
//	}
//
//	public void setStartTime(LocalTime startTime) {
//		this.startTime = startTime;
//	}
//
//	public LocalTime getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(LocalTime endTime) {
//		this.endTime = endTime;
//	}

	public String getEvtName() {
		return evtName;
	}

	public void setEvtName(String evtName) {
		this.evtName = evtName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	//	public String getRoom(){
//		return room;
//	}
//	
//	public void setRoom(String room){
//		this.room=room;
//	}
//	
//	public OrderStatus getStatus(){
//		return status;
//	}
//	
//	public void setStatus(OrderStatus status){
//		this.status=status;
//	}
}


