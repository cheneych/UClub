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

	GridLayout grid=new GridLayout(2,7);

	CheckBoxGroup<String> itemCBG=new CheckBoxGroup<>("Item Name");

	//TextField numPeople=new TextField("# People");
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
	
	CategoryDataService service = new CategoryDataService();
	DescripDataService service2=new DescripDataService(service.CateList.get(0).getHeadertypeid());
	ItemsDataService service3 = new ItemsDataService(service2.DesList.get(0).getServtypeid());
	
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
		final HorizontalLayout layout5=new HorizontalLayout();
		layout5.addComponents(comfirm,form);
		layout3.addComponents(finish,itemGrid,layout5);
		layout3.setComponentAlignment(finish, Alignment.TOP_RIGHT);
		layout3.setComponentAlignment(itemGrid, Alignment.TOP_RIGHT);
		layout5.setComponentAlignment(comfirm, Alignment.TOP_LEFT);
		layout5.setComponentAlignment(form, Alignment.TOP_RIGHT);
		final HorizontalLayout layout4=new HorizontalLayout();
		layout4.addComponents(grid,itemCBG,layout3);
		layout4.setSizeFull();
		layout4.setComponentAlignment(layout3, Alignment.TOP_RIGHT);
		layout4.setComponentAlignment(itemCBG, Alignment.TOP_LEFT);
		//fifth layer

		//addComponents(resDtl,layout4,listGrid,form2);
		addComponents(resDtl,layout4,listGrid);
	}
	public void update() {
		itemGrid.setItems(itemsAll);
	}

	private void dataProcess() {
		//grid
		grid.addComponent(booking, 0,0);
		grid.addComponent(cato,0,1);
		grid.addComponent(serv,0,2);
		grid.addComponent(sDate,0,3);
		grid.addComponent(eDate,0,4);
		grid.addComponent(viewall,0,5);
		//listGrid
		listGrid.setVisible(false);
		listGrid.setWidth("665");
		listGrid.setColumns("des","ctg","start","end");
		//itemGrid
		itemGrid.setColumns("servitemname","servitemchrg","qty","total");
		itemGrid.setVisible(false);
		
		//forms
		form.setVisible(false);
		form2.setVisible(false);
		//comfirm
		comfirm.setVisible(false);
		
		//category
//		CategoryDataService service = new CategoryDataService();
		for (int i=0;i<service.CateList.size();i++)
			category.add(service.CateList.get(i).getHeaderdesc());
		//serviceDes
//		DescripDataService service2=new DescripDataService(service.CateList.get(0).getHeadertypeid());
		for (int i=0;i<service2.DesList.size();i++)
			serviceDes.add(service2.DesList.get(i).getServtype());
		//cato
		cato.setItems(category);
		cato.setSelectedItem(category.get(0));
		cato.setEmptySelectionAllowed(false);
		//serv
		serv.setItems(serviceDes);
		serv.setSelectedItem(serviceDes.get(0));
		serv.setEmptySelectionAllowed(false);
		//item
//		ItemsDataService service3 = new ItemsDataService(service2.DesList.get(0).getServtypeid());
		for (int i=0;i<service3.ItemList.size();i++)
			itemNames.add(service3.ItemList.get(i).getServitemname());
		itemCBG.setItems(itemNames);
		itemCBG.setVisible(false);
	}

	private void eventProcess() {
		//item comboboxgroup
		itemCBG.addSelectionListener(e->{
			List<String> listSel=new ArrayList<String>(itemCBG.getSelectedItems());
			for (int j=0;j<service3.ItemList.size();j++) {
				String s=service3.ItemList.get(j).getServitemname();
				if (listSel.contains(s) && !itemList.containsKey(s)) {
					itemList.put(s, next++);
					itemsAll.add(service3.ItemList.get(j));
				}
				else if (!listSel.contains(s) && itemList.containsKey(s)) {
					for (Items i:itemsAll)
						if (i.getServitemname().equals(s)) {
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
				System.out.println("look");
				form.setItems(e.getValue());
			}
		});
		//comfirm
		comfirm.addClickListener(e->{
			itemCBG.setVisible(false);
			itemGrid.setVisible(false);
			//System.out.println(itemCBG.getSelectedItems().size());
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
					DescripDataService service2t=new DescripDataService(service.CateList.get(i).getHeadertypeid());
					service2=service2t;
					for (int j=0;j<service2t.DesList.size();j++)
						serviceDes.add(service2t.DesList.get(j).getServtype());
					break;
				}
			}
			serv.setItems(serviceDes);
			serv.setSelectedItem(serviceDes.get(0));
			serv.setEmptySelectionAllowed(false);
			itemCBG.setVisible(false);
			itemGrid.setVisible(false);
			comfirm.setVisible(false);
		});
		//serv
		serv.addValueChangeListener(e->{
			String sd=e.getValue();
			itemNames.clear();
			for (int i=0;i<service2.DesList.size();i++)
				if (service2.DesList.get(i).getServtype().equals(sd)) {
					ItemsDataService service3t = new ItemsDataService(service2.DesList.get(i).getServtypeid());
					service3=service3t;
					break;
				}
			
			for (int i=0;i<service3.ItemList.size();i++) {
				itemNames.add(service3.ItemList.get(i).getServitemname().toLowerCase());
				System.out.println(service3.ItemList.get(i).getServitemname().toLowerCase());
			}
			itemCBG.setItems(itemNames);
			itemCBG.setVisible(false);
			itemGrid.setVisible(false);
			comfirm.setVisible(false);
		});
		//view all
		viewall.addClickListener(e->{
			itemCBG.setVisible(true);
			itemGrid.setVisible(true);
			comfirm.setVisible(true);
			listGrid.setVisible(false);
			form2.setVisible(false);
		});
		finish.addClickListener(e->{
			MyUI.navigateTo("home");
		});
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		System.err.println(event.getParameters());		
	}
}
