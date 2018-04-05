package raymond.TestHomePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import raymond.TestDetails.Items;

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
	Grid<Order> grid=new Grid<>(Order.class);
	public List<Order> orders=new ArrayList<>();
	
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
		
		date.addValueChangeListener(e->{
			List<Order> newOrders=new ArrayList<>();
			String s=e.getValue().toString();
			for (Order o:orders) {
				if (o.getDate().equals(s)) {
					newOrders.add(o);
				}
			}
			grid.setItems(newOrders);
		});
		
	}

	private void dataProcess() {
		//order
		orders.add(new Order("2018-04-01","14:00","15:00","Ball","Great Room 202",OrderStatus.Comfirmed));
		orders.add(new Order("2018-03-27","19:00","22:00","Party","Great Room 203",OrderStatus.Comfirmed));
		orders.add(new Order("2018-03-27","8:00","11:00","Show","Jesse Hall",OrderStatus.Comfirmed));
		//menubar
		MenuItem name=barmenu.addItem("Test",null,null);
		name.setIcon(VaadinIcons.USER);
		MenuItem account1=name.addItem("My Account",null,null);
		MenuItem signout1=name.addItem("Sign Out",null,null);
		//grid
		grid.setColumns("startTime","endTime","des","room","status");
		grid.setSizeFull();
		grid.setItems(orders);
		//date
		date.setValue(LocalDate.now());
	}
}
