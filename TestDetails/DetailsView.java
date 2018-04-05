package raymond.TestDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import raymond.TestHomePage.*;


public class DetailsView extends VerticalLayout implements View {
	//Components
	public Grid<Lists> listGrid=new Grid<>(Lists.class);
	public Grid<Items> itemGrid=new Grid<>(Items.class);

	Label logo=new Label("University of Missouri");
	Label resDtl=new Label("Reservation Details");
	Label booking=new Label("Booking Name");

	DateTimeField sDate = new DateTimeField("Start Time", LocalDateTime.now());
	DateTimeField eDate = new DateTimeField("End Time", LocalDateTime.now());

	Button viewall=new Button("View All");
	Button comfirm=new Button("Comfirm");
	Button finish=new Button("Finish");

	ComboBox<String> cato=new ComboBox<>("Catogory");
	ComboBox<String> serv=new ComboBox<>("service Description");

	MenuBar barmenu=new MenuBar();

	GridLayout grid=new GridLayout(3,4);

	CheckBoxGroup<String> itemCBG=new CheckBoxGroup<>("Item Name");

	TextField numPeople=new TextField("# People");
	//global variables
	List<Items> i1=new ArrayList<>(Arrays.asList(new Items("cake",12.5),new Items("Brownie",6.0),new Items("Donuts",7.5)));
	List<Items> i2=new ArrayList<>(Arrays.asList(new Items("Beef",10),new Items("Pork",6.0),new Items("Shrimp",9.5)));
	List<Items> i3=new ArrayList<>(Arrays.asList(new Items("Champagne",18),new Items("Sangria",16.5),new Items("Bottled Water",2)));
	
	public ItemsForm form=new ItemsForm(this);
	public ListsForm form2=new ListsForm(this);

	public HashMap<String, Double> itemTb = new HashMap<>();
	public HashMap<String,Integer> itemList=new HashMap<>();

	public int next=0;
	public int listnxt=0;

	public List<Items> itemsAll=new ArrayList<>();	
	public List<Lists> lists=new ArrayList<>();
	
	List<String> category=new ArrayList<>();
	List<String> serviceDes=new ArrayList<>();
	List<String> itemNames=new ArrayList<String>();
	
	List<String> sd1=new ArrayList<>(Arrays.asList("Dessert","Dinner","Dinner-Buffet"));
	List<String> sd2=new ArrayList<>(Arrays.asList("Cash Bar","Host Bar","Package Bar"));
	List<String> sd3=new ArrayList<>(Arrays.asList("Balloons","FireWorks","Audio & Visual"));

	public DetailsView() {
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

		//thirds layer

		//forth layer
		final VerticalLayout layout3=new VerticalLayout();
		layout3.addComponents(finish,itemGrid,comfirm,form);
		layout3.setComponentAlignment(finish, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(itemGrid, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(comfirm, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(form, Alignment.TOP_RIGHT);
		final HorizontalLayout layout4=new HorizontalLayout();
		layout4.addComponents(grid,itemCBG,layout3);
		layout4.setSizeFull();
		layout4.setComponentAlignment(layout3, Alignment.TOP_RIGHT);
		layout4.setComponentAlignment(itemCBG, Alignment.TOP_CENTER);
		//fifth layer

		addComponents(layout1,resDtl,layout4,listGrid,form2);
	}
	public void update() {
		itemGrid.setItems(itemsAll);
	}

	private void dataProcess() {
		//barmenu
		MenuItem name=barmenu.addItem("Test",null,null);
		name.setIcon(VaadinIcons.USER);
		MenuItem account1=name.addItem("My Account",null,null);
		MenuItem signout1=name.addItem("Sign Out",null,null);
		//grid
		grid.addComponent(booking, 0,0,2,0);
		grid.addComponent(cato,0,1);
		grid.addComponent(serv,0,2);
		grid.addComponent(viewall,1,2);
		grid.addComponent(sDate,0,3);
		grid.addComponent(eDate,1,3);
		grid.addComponent(numPeople,2,3);
		//listGrid
		listGrid.setVisible(false);
		listGrid.setWidth("665");
		listGrid.setColumns("des","ctg","start","end");
		//itemGrid
		itemGrid.setColumns("name","price","qty","total");
		itemGrid.setVisible(false);
		//item
		itemCBG.setItems(itemNames);
		itemCBG.setVisible(false);
		//forms
		form.setVisible(false);
		form2.setVisible(false);
		//itemTb
		itemTb.put("cake", 12.5); 
		itemTb.put("Brownie", 6.0); 
		itemTb.put("Donuts", 7.5); 
		itemTb.put("Beef", 10.0); 
		itemTb.put("Pork", 6.0); 
		itemTb.put("Shrimp", 9.5); 
		itemTb.put("Champagne", 18.0); 
		itemTb.put("Sangria", 16.5); 
		itemTb.put("Bottled Water", 2.0); 
		//itemNames
		for (Items I:i1)
			itemNames.add(I.getName());   
		//category
		category.add("Food & Beverage");
		category.add("Bar Service");
		category.add("Equipment");
		//serviceDes
		serviceDes.addAll(sd1);
		//cato
		cato.setItems(category);
		cato.setSelectedItem("Food & Beverage");
		cato.setEmptySelectionAllowed(false);
		//serv
		serv.setItems(serviceDes);
		serv.setSelectedItem(serviceDes.get(0));
		serv.setEmptySelectionAllowed(false);
		//comfirm
		comfirm.setVisible(false);

	}

	private void eventProcess() {
		//item comboboxgroup
		itemCBG.addSelectionListener(e->{
			List<String> listSel=new ArrayList<String>(itemCBG.getSelectedItems());
			for (String s:itemNames) {
				if (listSel.contains(s) && !itemList.containsKey(s)) {
					itemList.put(s, next++);
					itemsAll.add(new Items(s,itemTb.get(s)));
				}
				else if (!listSel.contains(s) && itemList.containsKey(s)) {
					for (Items i:itemsAll)
						if (i.getName().equals(s)) {
							itemsAll.remove(i);
							break;
						}
					itemList.remove(s);
				}
			}
			update();
		});
		//item group
		itemGrid.asSingleSelect().addValueChangeListener(e->{
			if (e.getValue()==null) {
				form.setVisible(false);
			}else {
				form.setItems(e.getValue());
			}
		});
		//view all
		viewall.addClickListener(e->{
			itemCBG.setVisible(true);
			itemGrid.setVisible(true);
			comfirm.setVisible(true);
			listGrid.setVisible(false);
			form2.setVisible(false);
		});
		//comfirm
		comfirm.addClickListener(e->{
			itemCBG.setVisible(false);
			itemGrid.setVisible(false);
			System.out.println(itemCBG.getSelectedItems().size());
			if (itemCBG.getSelectedItems().size()>0)
				lists.add(new Lists(cato.getValue(),serv.getValue(),sDate.getValue(),eDate.getValue(),listnxt++));
			itemCBG.deselectAll();
			
			listGrid.setVisible(true);
			listGrid.setItems(lists);
			
			comfirm.setVisible(false);
			form.setVisible(false);
			
			itemsAll.clear();
			itemList.clear();
			update();
		});
		//listgrid
		listGrid.asSingleSelect().addValueChangeListener(e->{
			if (e.getValue()==null) {
				form2.setVisible(false);
			}else {
				form2.setLists(e.getValue());
			}
		});
		//cato
		cato.addValueChangeListener(e->{
			String ctg=e.getValue();
			serviceDes.clear();
			switch(ctg) {
				case "Food & Beverage":
					serviceDes.addAll(sd1);
					break;
				case "Bar Service":
					serviceDes.addAll(sd2);
					break;
				case "Equipment":
					serviceDes.addAll(sd3);
					break;
				default:
					break;
			}
			serv.setItems(serviceDes);
			serv.setSelectedItem(serviceDes.get(0));
			serv.setEmptySelectionAllowed(false);
		});
		//serv
		serv.addValueChangeListener(e->{
			String sd=e.getValue();
			itemNames.clear();
			switch(sd) {
				case "Dessert":
					for (Items I:i1)
						itemNames.add(I.getName()); 
					break;
				case "Dinner":
					for (Items I:i2)
						itemNames.add(I.getName()); 
					break;
				case "Host Bar":
					for (Items I:i3)
						itemNames.add(I.getName()); 
					break;
				default:
					break;
			}
			itemCBG.setItems(itemNames);
			itemCBG.setVisible(false);
		});
		
		finish.addClickListener(e->{
			
		});
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.err.println(event.getParameters());		
	}
}
