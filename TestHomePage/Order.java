package raymond.TestHomePage;

import java.util.Date;

public class Order {
	private String day;
	private String startTime;
	private String endTime;
	private String des;
	private String room;
	private OrderStatus status;
	
	public Order(String day,String startTime,String endTime,String des,String room,OrderStatus status) {
		this.day=day;
		this.startTime=startTime;
		this.endTime=endTime;
		this.des=des;
		this.room=room;
		this.status=status;
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
	
	public String getDes(){
		return des;
	}
	
	public void setDes(String des){
		this.des=des;
	}
	
	public String getRoom(){
		return room;
	}
	
	public void setRoom(String room){
		this.room=room;
	}
	
	public OrderStatus getStatus(){
		return status;
	}
	
	public void setStatus(OrderStatus status){
		this.status=status;
	}
}


