package raymond.TestReserve;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
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
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;

import raymond.Test.MyUI;
import raymond.Test.TopBarView;

@SuppressWarnings("serial")
public class ReservationView extends TopBarView implements View {
	//Components
	Label DAT = new Label("Date & Time");

	TextField booking=new TextField("Booking");
	TextField cName=new TextField("Custormer Name");
	TextField dates=new TextField("Dates");
	TextField nPhone=new TextField("Phone Number");

//	Tree<String> room = new Tree<>();
//	TreeData<String> roomData = new TreeData<>();
//	TreeDataProvider inMemoryDataProvider = new TreeDataProvider<>(roomData);
	
	TreeGrid<Room> treeGrid=new TreeGrid<>();
	TreeDataProvider<Room> dataProvider = (TreeDataProvider<Room>) treeGrid.getDataProvider();
	TreeData<Room> data = dataProvider.getTreeData();

	DateTimeField sDate = new DateTimeField("Start Time", LocalDateTime.now());
	DateTimeField eDate = new DateTimeField("End Time", LocalDateTime.now());

	Button button=new Button("Search Rooms");
	Button nStep=new Button("Next Step");

	FormLayout form1=new FormLayout();
	FormLayout form2=new FormLayout();

	//GridLayout grid=new GridLayout(2,2);

	//ComboBox<String> select=new ComboBox<>("Create booking in this time zone");
	//Global Variable
	//List<String> zone=new ArrayList<>();

	public ReservationView() {
		init();
	}

	public void init() {
		dataProcess();
		eventProcess();
		//first layer
		//second layer
		final HorizontalLayout layout1=new HorizontalLayout();
		final VerticalLayout layout4=new VerticalLayout();
		final HorizontalLayout layout2=new HorizontalLayout();
		layout1.addComponents(sDate,eDate);
		layout4.addComponents(DAT,layout1);
		layout2.addComponents(form1,form2,layout4,nStep);
		layout2.setSizeFull();
		layout2.setComponentAlignment(nStep, Alignment.MIDDLE_RIGHT);
		//third layer

		//forth layer
		final VerticalLayout layout3=new VerticalLayout();
		layout3.addComponents(button,treeGrid);
		layout3.setSizeFull();

		addComponents(layout2,layout3);	

	}

	private void dataProcess() {
		//room
//		roomData.addItem(null,"Ball Room");
//		roomData.addItem(null,"Study Room");
//		roomData.addItem(null,"Dining Room");
//		roomData.addItem("Ball Room","2205B");
//		roomData.addItem("Study Room","2201A");
//		room.setDataProvider(inMemoryDataProvider);
		//treegrid
		//treeGrid.setItems();
		treeGrid.setVisible(false);
		treeGrid.setSizeFull();
		treeGrid.addColumn(Room::getRoom).setCaption("Room");
		treeGrid.addColumn(Room::getA4).setCaption("4a");
		treeGrid.addColumn(Room::getA5).setCaption("5a");
		treeGrid.addColumn(Room::getA6).setCaption("6a");
		treeGrid.addColumn(Room::getA7).setCaption("7a");
		treeGrid.addColumn(Room::getA8).setCaption("8a");
		treeGrid.addColumn(Room::getA9).setCaption("9a");
		treeGrid.addColumn(Room::getA10).setCaption("10a");
		treeGrid.addColumn(Room::getA11).setCaption("11a");
		treeGrid.addColumn(Room::getA12).setCaption("12a");
		treeGrid.addColumn(Room::getP1).setCaption("1p");
		treeGrid.addColumn(Room::getP2).setCaption("2p");
		treeGrid.addColumn(Room::getP3).setCaption("3p");
		treeGrid.addColumn(Room::getP4).setCaption("4p");
		treeGrid.addColumn(Room::getP5).setCaption("5p");
		treeGrid.addColumn(Room::getP6).setCaption("6p");
		treeGrid.addColumn(Room::getP7).setCaption("7p");
		treeGrid.addColumn(Room::getP8).setCaption("8p");
		treeGrid.addColumn(Room::getP9).setCaption("9p");
		treeGrid.addColumn(Room::getP10).setCaption("10p");
		treeGrid.addColumn(Room::getP11).setCaption("11p");
		treeGrid.addColumn(Room::getP12).setCaption("12p");
		treeGrid.addColumn(Room::getA1).setCaption("1a");
		treeGrid.addColumn(Room::getA2).setCaption("2a");
		treeGrid.addColumn(Room::getA3).setCaption("3a");
		// add new items
		Room ballroom=new Room("Ball Room");
		Room studyroom=new Room("Study Room");
		Room diningroom=new Room("Dining Room");
		Room br1=new Room("2205A");
		Room br2=new Room("2205B");
		data.addItem(null,ballroom);
		data.addItems(null,studyroom);
		data.addItems(null,diningroom);
		data.addItems(ballroom,br1,br2);
		dataProvider.refreshAll();
		//grid
//		grid.addComponent(DAT,0,0,1,0);
//		grid.addComponent(sDate,0,1);
//		grid.addComponent(eDate,1,1);
		//grid.addComponent(select,0,3,1,3);
//		grid.addComponent(new Label("Room"),0,0);
//		grid.addComponent(button,1,1);
		//zone
//		zone.add("Central Time");
//		zone.add("Pacific Time");
//		zone.add("East Time");
		//select
//		select.setItems(zone);
//		select.setSelectedItem("Central Time");
//		select.setEmptySelectionAllowed(false);
		//form1
		form1.addComponents(booking,cName);
		form2.addComponents(dates,nPhone);
	}

	private void eventProcess() {
		button.addClickListener(e->{
			treeGrid.setVisible(true);
		});

		nStep.addClickListener(e->{
			MyUI.navigateTo("details","test");
		});
	}

}
