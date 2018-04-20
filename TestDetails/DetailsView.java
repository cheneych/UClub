package raymond.TestDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.provider.DataProvider;
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

import raymond.Test.MyUI;
import raymond.Test.TopBarView;
import raymond.TestHomePage.*;
import raymond.dataprovider.filter.Filter;


public class DetailsView extends TopBarView implements View {
	//Components
	public Grid<Lists> listGrid=new Grid<>(Lists.class);
	public Grid<Items> itemGrid=new Grid<>(Items.class);

	Label resDtl=new Label("Reservation Details");
	Label booking=new Label("Booking Name");

	DateTimeField sDate = new DateTimeField("Start Time", LocalDateTime.now());
	DateTimeField eDate = new DateTimeField("End Time", LocalDateTime.now());

	Button viewall=new Button("View All");
	Button comfirm=new Button("Comfirm");
	Button finish=new Button("Finish");

	ComboBox<String> cato=new ComboBox<>("Catogory");
	ComboBox<String> serv=new ComboBox<>("service Description");

	GridLayout grid=new GridLayout(3,4);

	CheckBoxGroup<String> itemCBG=new CheckBoxGroup<>("Item Name");

	TextField numPeople=new TextField("# People");
	//global variables
	
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

		addComponents(resDtl,layout4,listGrid,form2);
	}
	public void update() {
		itemGrid.setItems(itemsAll);
	}

	private void dataProcess() {
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
		ItemsDataService service3 = new ItemsDataService(DescripDataService.DesList.get(0).getServtypeid());
		for (int i=0;i<ItemsDataService.ItemList.size();i++)
			itemNames.add(ItemsDataService.ItemList.get(i).getServitemname());
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
	 
		//category
		CategoryDataService service = new CategoryDataService();
		for (int i=0;i<CategoryDataService.CateList.size();i++)
			category.add(CategoryDataService.CateList.get(i).getHeaderdesc());
		//serviceDes
		DescripDataService service2=new DescripDataService(CategoryDataService.CateList.get(0).getHeadertypeid());
		for (int i=0;i<DescripDataService.DesList.size();i++)
			serviceDes.add(DescripDataService.DesList.get(i).getServtype());
		//cato
		cato.setItems(category);
		cato.setSelectedItem(category.get(0));
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
//		itemCBG.addSelectionListener(e->{
//			List<String> listSel=new ArrayList<String>(itemCBG.getSelectedItems());
//			for (String s:itemNames) {
//				if (listSel.contains(s) && !itemList.containsKey(s)) {
//					itemList.put(s, next++);
//				}
//				else if (!listSel.contains(s) && itemList.containsKey(s)) {
//					for (Items i:itemsAll)
//						if (i.getName().equals(s)) {
//							itemsAll.remove(i);
//							break;
//						}
//					itemList.remove(s);
//				}
//			}
//			update();
//		});
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
			for (int i=0;i<category.size();i++) {
				if (category.get(i).equals(ctg)) {
					DescripDataService service2=new DescripDataService(CategoryDataService.CateList.get(i).getHeadertypeid());
					for (int j=0;j<DescripDataService.DesList.size();j++)
						serviceDes.add(DescripDataService.DesList.get(j).getServtype());
					break;
				}
			}
			serv.setItems(serviceDes);
			serv.setSelectedItem(serviceDes.get(0));
			serv.setEmptySelectionAllowed(false);
		});
		//serv
//		serv.addValueChangeListener(e->{
//			String sd=e.getValue();
//			itemNames.clear();
//			switch(sd) {
//				case "Dessert":
//					for (Items I:i1)
//						itemNames.add(I.getName()); 
//					break;
//				case "Dinner":
//					for (Items I:i2)
//						itemNames.add(I.getName()); 
//					break;
//				case "Host Bar":
//					for (Items I:i3)
//						itemNames.add(I.getName()); 
//					break;
//				default:
//					break;
//			}
//			itemCBG.setItems(itemNames);
//			itemCBG.setVisible(false);
//		});
		
		finish.addClickListener(e->{
			MyUI.navigateTo("home");
		});
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.err.println(event.getParameters());		
	}
}
