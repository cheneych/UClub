package raymond.TestDetails;

public class Items {
	private String name;
	private int Qty;
	private double price;
	private double total;
	private Long id;
	
	Items(String name,int Qty,double price,double total,Long id){
		this.name=name;
		this.Qty=Qty;
		this.price=price;
		this.total=total;
		this.id=id;
	}
	
	Items(String name){
		this.name=name;
	}
	

	Items(String name,double price){
		this.name=name;
		this.price=price;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getQty() {
		return Qty;
	}
	
	public void setQty(int Qty) {
		this.Qty=Qty;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price=price;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total=total;
	}
}
