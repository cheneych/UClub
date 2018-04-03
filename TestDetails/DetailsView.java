package raymond.TestDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
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
		
	ComboBox<String> cato=new ComboBox<>("Catogory");
	ComboBox<String> serv=new ComboBox<>("service Description");
	
	MenuBar barmenu=new MenuBar();
	
	GridLayout grid=new GridLayout(3,4);
	
	CheckBoxGroup<String> item=new CheckBoxGroup<>("Item Name");
	
	TextField numPeople=new TextField("# People");
	//global variables
	Items i1=new Items("cake",12.5); Items i2=new Items("Brownie",6.0); Items i3=new Items("Donuts",7.5);
	
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
		layout3.addComponents(itemGrid,comfirm,form);
		layout3.setComponentAlignment(itemGrid, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(comfirm, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(form, Alignment.TOP_RIGHT);
		final HorizontalLayout layout4=new HorizontalLayout();
		layout4.addComponents(grid,item,layout3);
		layout4.setSizeFull();
		layout4.setComponentAlignment(layout3, Alignment.TOP_RIGHT);
		layout4.setComponentAlignment(item, Alignment.TOP_CENTER);
		//fifth layer
		
		addComponents(layout1,resDtl,layout4,listGrid,form2);
	}
	public void update() {
		itemGrid.setItems(itemsAll);
	}
	
	private void dataProcess() {
		MenuItem name=barmenu.addItem("Test",null,null);
		name.setIcon(VaadinIcons.USER);
		MenuItem account1=name.addItem("My Account",null,null);
		MenuItem signout1=name.addItem("Sign Out",null,null);
		
		grid.addComponent(booking, 0,0,2,0);
		grid.addComponent(cato,0,1);
		grid.addComponent(serv,0,2);
		grid.addComponent(viewall,1,2);
		grid.addComponent(sDate,0,3);
		grid.addComponent(eDate,1,3);
		grid.addComponent(numPeople,2,3);
		
		listGrid.setVisible(false);
		listGrid.setWidth("665");
		listGrid.setColumns("des","ctg","start","end");
		
		itemGrid.setColumns("name","price","qty","total");
		itemGrid.setVisible(false);
		
		item.setItems(itemNames);
		item.setVisible(false);
		
		form.setVisible(false);
		form2.setVisible(false);
		
		itemTb.put("cake", 12.5); 
		itemTb.put("Brownie", 6.0); 
		itemTb.put("Donuts", 7.5); 
		
		itemNames.add(i1.getName()); 
		itemNames.add(i2.getName()); 
		itemNames.add(i3.getName());  

		category.add("Food & Beverage");
		
		cato.setItems(category);
		
		serviceDes.add("Dessert");
		
		serv.setItems(serviceDes);
		
		comfirm.setVisible(false);
	}

	private void eventProcess() {
		item.addSelectionListener(e->{
			List<String> listSel=new ArrayList<String>(item.getSelectedItems());
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
		
		itemGrid.asSingleSelect().addValueChangeListener(e->{
			if (e.getValue()==null) {
				form.setVisible(false);
			}else {
				form.setItems(e.getValue());
			}
		});
		
		viewall.addClickListener(e->{
			item.setVisible(true);
			itemGrid.setVisible(true);
			comfirm.setVisible(true);
			listGrid.setVisible(false);
		});

		comfirm.addClickListener(e->{
			item.setVisible(false);
			itemGrid.setVisible(false);
			comfirm.setVisible(false);
			form.setVisible(false);
			listGrid.setVisible(true);
			lists.add(new Lists(cato.getValue(),serv.getValue(),sDate.getValue(),eDate.getValue(),listnxt++));
			listGrid.setItems(lists);
		});
		
		listGrid.asSingleSelect().addValueChangeListener(e->{
			if (e.getValue()==null) {
				form2.setVisible(false);
			}else {
				form2.setLists(e.getValue());
			}
		});
	}
}
