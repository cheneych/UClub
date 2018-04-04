package raymond.TestReserve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import raymond.Test.MyUI;

@SuppressWarnings("serial")
public class ReservationView extends VerticalLayout implements View {
	//Components
	Label logo=new Label("University of Missouri");
	Label DAT = new Label("Date & Time");

	TextField booking=new TextField("Booking");
	TextField cName=new TextField("Custormer Name");
	TextField dates=new TextField("Dates");
	TextField nPhone=new TextField("Phone Number");

	Tree<String> room = new Tree<>();
	TreeData<String> roomData = new TreeData<>();
	TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(roomData);

	DateTimeField sDate = new DateTimeField("Start Time", LocalDateTime.now());
	DateTimeField eDate = new DateTimeField("End Time", LocalDateTime.now());

	Button button=new Button("Search");
	Button nStep=new Button("Next Step");

	FormLayout form1=new FormLayout();
	FormLayout form2=new FormLayout();

	GridLayout grid=new GridLayout(2,6);

	MenuBar barmenu=new MenuBar();

	ComboBox<String> select=new ComboBox<>("Create booking in this time zone");
	//Global Variable
	List<String> zone=new ArrayList<>();

	public ReservationView() {
		init();
	}

	public void init() {
		dataProcess();
		eventProcess();
		//first layer
		final HorizontalLayout layout1=new HorizontalLayout(); 
		layout1.addComponents(logo,barmenu);
		layout1.setSizeFull();
		layout1.setComponentAlignment(barmenu, Alignment.TOP_RIGHT);
		//second layer
		final HorizontalLayout layout2=new HorizontalLayout();
		layout2.addComponents(form1,form2,nStep);
		layout2.setSizeFull();
		layout2.setComponentAlignment(nStep, Alignment.MIDDLE_RIGHT);
		//third layer

		//forth layer
		final HorizontalLayout layout3=new HorizontalLayout();
		layout3.addComponents(grid,room);
		layout3.setSizeFull();

		addComponents(layout1, layout2,layout3);	

	}

	private void dataProcess() {
		//barmenu
		MenuItem name=barmenu.addItem("Test",null,null);
		name.setIcon(VaadinIcons.USER);
		MenuItem account1=name.addItem("My Account",null,null);
		MenuItem signout1=name.addItem("Sign Out",null,null);
		//room
		room.setVisible(false);
		roomData.addItem(null,"Ball Room");
		roomData.addItem(null,"Study Room");
		roomData.addItem(null,"Dining Room");
		roomData.addItem("Ball Room","2205B");
		roomData.addItem("Study Room","2201A");
		room.setDataProvider(inMemoryDataProvider);
		//grid
		grid.addComponent(DAT,0,0,1,0);
		grid.addComponent(sDate,0,1);
		grid.addComponent(eDate,1,1);
		grid.addComponent(select,0,3,1,3);
		grid.addComponent(new Label("Room"),0,4);
		grid.addComponent(button,1,5);
		//zone
		zone.add("Central Time");
		zone.add("Pacific Time");
		zone.add("East Time");
		//select
		select.setItems(zone);
		select.setSelectedItem("Central Time");
		select.setEmptySelectionAllowed(false);
		//form1
		form1.addComponents(booking,cName);
		form2.addComponents(dates,nPhone);
	}

	private void eventProcess() {
		button.addClickListener(e->{
			room.setVisible(true);
		});

		nStep.addClickListener(e->{
			MyUI.navigateTo("details");
		});
	}

}
