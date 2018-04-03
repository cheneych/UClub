package raymond.TestHomePage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import raymond.Test.*;

@SuppressWarnings("serial")
public class HomeView extends VerticalLayout implements View {
	//Components
	private Button reserve=new Button("Reservation");
	private Button home=new Button("HOME");

	Label logo=new Label("University of Missouri");
	Label pstbkg=new Label("Past Bookings");
	
	MenuBar barmenu=new MenuBar();
	
	DateField date = new DateField();
	//global variable
	Order order=new Order();
	Grid<Order> grid=new Grid<>(Order.class);
	List<Order> orders = new ArrayList<>();
	
	public HomeView() {
		init();
	}

	public void init() {
		eventProcess();
		dataProcess();
		//first layer
		final HorizontalLayout layout1=new HorizontalLayout(); 
		layout1.addComponents(logo,barmenu);
		layout1.setSizeFull();
		layout1.setComponentAlignment(barmenu, Alignment.TOP_RIGHT);
		//second layer
		final HorizontalLayout layout2=new HorizontalLayout(); 
		final VerticalLayout layout3 = new VerticalLayout();
		final VerticalLayout layout4 = new VerticalLayout();
		layout3.addComponents(home,reserve);
		layout4.addComponents(pstbkg,date,grid);
		layout2.setSizeFull();
		layout2.addComponents(layout3,layout4);

		addComponents(layout1,layout2);
	}

	private void eventProcess(){
		home.addClickListener(e->{
			MyUI.navigateTo("home"); 
		});        

		reserve.addClickListener(e->{
			MyUI.navigateTo("reservation"); 
		});
	}

	private void dataProcess() {
		//order
		order.setStartTime("14:00");
		order.setEndTime("15:00");
		order.setDes("Ball");
		order.setRoom("Great Room 202");
		order.setStatus(OrderStatus.Comfirmed);
		//menubar
		MenuItem name=barmenu.addItem("Test",null,null);
		name.setIcon(VaadinIcons.USER);
		MenuItem account1=name.addItem("My Account",null,null);
		MenuItem signout1=name.addItem("Sign Out",null,null);
		//grid
		grid.setColumns("startTime","endTime","des","room","status");
		grid.setSizeFull();
		orders.add(order);
		grid.setItems(orders);
		//date
		date.setValue(LocalDate.now());
	}
}
