package raymond.TestHomePage;

import java.util.Date;

public class Order {
	private String day;
	private String startTime;
	private String endTime;
	private String evtName;
	//private String room;
	//private OrderStatus status;
	
	public Order() {
		
	}
	
	public Order(String day,String startTime,String endTime,String des,String room,OrderStatus status) {
		this.day=day;
		this.startTime=startTime;
		this.endTime=endTime;
		this.evtName=evtName;
//		this.room=room;
//		this.status=status;
	}
	
	public String getDate() {
		return day;
	}
	
	public void setDate(String day) {
		this.day=day;
	}
	
	public String getStartTime(){
		return startTime;
	}
	
	public void setStartTime(String startTime){
		this.startTime=startTime;
	}
	
	public String getEndTime(){
		return endTime;
	}
	
	public void setEndTime(String endTime){
		this.endTime=endTime;
	}
	
	public String getevtName(){
		return evtName;
	}
	
	public void setevtName(String des){
		this.evtName=evtName;
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


