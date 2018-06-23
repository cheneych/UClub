package raymond.TestHomePage;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import raymond.ui.standardgrid.StandardGridConfigurator;
import raymond.Test.*;
import raymond.TestDetails.Items;
import raymond.dataprovider.filter.Filter;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Theme("mytheme")
//public class HomeView extends VerticalLayout implements View {
public class HomeView extends TopBarView implements View {
	final transient Logger logger = LoggerFactory.getLogger(HomeView.class);
	//Components
	private Button reserve=new Button("Create a new Reservation");
	private Button home=new Button("HOME");
	private Button dt=new Button("Search by Date");
	private Button cus=new Button("Search by Customer");

	TextField member=new TextField("Customer ID");
	TextField customer=new TextField();
	//TextField booking=new TextField("Booking Description");

	Label pstbkg=new Label("Past Bookings");
	
	DateField date = new DateField();
	
	private StandardGridConfigurator configurator;
	//global variable
	Grid<Order> grid=new Grid<Order>(Order.class);
	public List<Order> orders=new ArrayList<>();
	
	public HomeView() {
		init();
	}

	public void init() {
		eventProcess();
		dataProcess();
		//first layer
		//second layer
		final HorizontalLayout layout2=new HorizontalLayout(); 
		final VerticalLayout layout3 = new VerticalLayout();
		final VerticalLayout layout4 = new VerticalLayout();
		final HorizontalLayout layout5=new HorizontalLayout(); 
		final HorizontalLayout layout6=new HorizontalLayout(); 
		final HorizontalLayout layout7=new HorizontalLayout(); 
		layout5.addComponents(member);
		layout3.addComponents(home,layout5,reserve);
		layout6.addComponents(date,customer);
		layout7.addComponents(dt,cus);
		layout4.addComponents(pstbkg,layout7,layout6,grid);
		//layout2.setSizeFull();
		layout2.addComponents(layout3,layout4);

		addComponents(layout2);
	}

	private void eventProcess(){
		dt.addClickListener(e->{
			dt.setVisible(false);
			customer.setVisible(false);
			date.setVisible(true);
			cus.setVisible(true);
			grid.setVisible(true);
		});
		
		cus.addClickListener(e->{
			cus.setVisible(false);
			customer.setVisible(true);
			dt.setVisible(true);
			date.setVisible(false);
		});
		
		home.addClickListener(e->{
			MyUI.navigateTo("home"); 
		});        

		reserve.addClickListener(e->{
			String custid = member.getValue();
			if (!custid.equals("")) {
				IsValidCusService service = new IsValidCusService(custid);
				int count=service.getData();
				if (count == 1) {
					//set custid in session
					VaadinService.getCurrentRequest().getWrappedSession().setAttribute("cur_custid",custid);
					//insert into evt
					try {
						service.storeRow(custid);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					VaadinService.getCurrentRequest().getWrappedSession()
					 .setAttribute("create_or_modify",0);
					//redirect
					MyUI.navigateTo("reservation"); 
				}
			}
			else {
				//should be changed to pop up window later, now just for test purpose
				System.out.println("popup window");
				MyUI.navigateTo("reservation"); 
			}
		});
		
		date.addValueChangeListener(e->{
			String s=e.getValue().toString();
			System.out.println(s);
			OrderDataService service = new OrderDataService(s,"date");
			grid.setDataProvider(service.getDataProvider());
		});
		
		customer.addValueChangeListener(e->{
			String s=e.getValue().toString();
			OrderDataService service2 = new OrderDataService(s,"customer");
			DataProvider<Order, Filter> provider = DataProvider.fromFilteringCallbacks(query -> service2.fetch(query),query -> service2.count(query));
			grid.setDataProvider(provider);
			grid.setVisible(true);
		});
		
		grid.asSingleSelect().addValueChangeListener(e->{
			VaadinService.getCurrentRequest().getWrappedSession()
			 .setAttribute("custid",grid.asSingleSelect().getValue().getId());
			VaadinService.getCurrentRequest().getWrappedSession()
			 .setAttribute("evtid_modify",grid.asSingleSelect().getValue().getEvtid());
			VaadinService.getCurrentRequest().getWrappedSession()
			 .setAttribute("fid_modify",grid.asSingleSelect().getValue().getFid());
			VaadinService.getCurrentRequest().getWrappedSession()
			 .setAttribute("create_or_modify",1);
			MyUI.navigateTo("info"); 
		});
		
	}

	private void dataProcess() {
		//textfield
		member.setPlaceholder("Type Customer ID");
		customer.setVisible(false);
		customer.setPlaceholder("Type customer ID or Name");
		customer.setWidth("300px");

		//grid
		grid.setColumns("evtName","custName","day","id","evtid","fid");
		grid.getColumn("id").setHidden(true);
		grid.getColumn("evtid").setHidden(true);
		grid.getColumn("fid").setHidden(true);
		grid.setVisible(false);
		grid.setSizeFull();
		grid.setWidth("1000px");
		//date
		date.setVisible(false);
		date.setValue(LocalDate.now());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}
}
