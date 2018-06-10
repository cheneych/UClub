package raymond.TestInfo;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Text;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextArea;

import raymond.ui.standardgrid.StandardGridConfigurator;
import raymond.Test.*;
import raymond.TestDetails.Items;
import raymond.TestHomePage.OrderDataService;
import raymond.dataprovider.filter.Filter;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@Theme("mytheme")
public class InfoView extends TopBarView implements View {
	//Components	
	TabSheet tabsheet=new TabSheet();
	//evt info
	Label uc=new Label("University Club");
	TextField bookName=new TextField("Booking Name");
	TextField postAs=new TextField("Post As/Readerboard");
	TextField bookId=new TextField("Booking ID");
	TextField funcName=new TextField("Function Name");
	TextField functPost=new TextField("Function Post As");
	TextField funcId=new TextField("Function ID");
	TextField status=new TextField("Status");
	TextField confirmDt=new TextField("Confirm Date");
	TextField dt=new TextField("Date");	
	Button evt_modify=new Button("Modify");
	//basic info
		//customer
	Label cusInfo=new Label("Basic Info");
	Label cusAdd1=new Label("Site Address");
	Label cusAdd2=new Label("Billing Address");
	Label cusAct=new Label("Custom Activity");
	TextField cname=new TextField("Customer");
	TextField phone=new TextField("Phone");
	TextField fax=new TextField("Fax");
	TextField add1=new TextField("Address 1");
	TextField add2=new TextField("Address 2");
	TextField city=new TextField("City");
	TextField state=new TextField("State");
	TextField zip=new TextField("Zip");
	TextField country=new TextField("Country");
	TextField add1b=new TextField("Address 1");
	TextField add2b=new TextField("Address 2");
	TextField cityb=new TextField("City");
	TextField stateb=new TextField("State");
	TextField zipb=new TextField("Zip");
	TextField countryb=new TextField("Country");
	TextField cmail=new TextField("E-mail");
		//salesperson
	TextField contact=new TextField("Contact");
	TextField osContact=new TextField("On-Site Contact");
	TextField sPhone=new TextField("Phone");
	TextField mail=new TextField("E-mail");
	TextField sPer=new TextField("SalesPerson");
	TextField bookMgr=new TextField("Booking Mgr");
		//room info
	TextField st=new TextField("Start Time");
	TextField et=new TextField("End Time");
	TextField exp=new TextField("#Expected/Set");
	TextField gua=new TextField("#Guaranteed");
	TextField style=new TextField("Setup Style");
	TextField room=new TextField("Room(s)");
	TextField sec=new TextField("Section(s)");
	TextArea notes=new TextArea("Function Notes");
	Button room_modify=new Button("Modify");
	
	public InfoView()  {
		init();
	}

	public void init()  {
		eventProcess();
		dataProcess();
		GridLayout grid=new GridLayout(3,7);
		grid.addComponent(uc,0,0);
		grid.addComponent(dt,0,3);
		grid.addComponent(status,0,4);
		grid.addComponent(confirmDt,0,5);
		grid.addComponent(bookName,1,0);
		grid.addComponent(postAs,1,1);
		grid.addComponent(bookId,1,2);
		grid.addComponent(funcName,1,3);
		grid.addComponent(functPost,1,4);
		grid.addComponent(funcId,1,5);
		grid.addComponent(evt_modify,2,6);

		tabsheet.addTab(grid,"Evt Info");
		
		VerticalLayout tab2=new VerticalLayout();
		HorizontalLayout l1=new HorizontalLayout();
		HorizontalLayout l2=new HorizontalLayout();
		HorizontalLayout l3=new HorizontalLayout();
		HorizontalLayout l4=new HorizontalLayout();
		HorizontalLayout l5=new HorizontalLayout();
		l1.addComponents(cname,phone,fax,cmail);
		l2.addComponents(add1,add2,city,state,zip,country);
		l3.addComponents(add1b,add2b,cityb,stateb,zipb,countryb);
//		l2.addComponents(contact,sPhone,mail);
//		l3.addComponents(osContact,sPer,bookMgr);
//		l4.addComponents(st,et);
//		l5.addComponents(exp,gua,style,room, sec);
		tab2.addComponents(cusInfo,l1,cusAdd1,l2,cusAdd2,l3,cusAct,l4);
		tabsheet.addTab(tab2,"Customer Info");
		
		VerticalLayout tab3=new VerticalLayout();
//		tab3.addComponents();
		tabsheet.addTab(tab3,"Sales Info");
		
		VerticalLayout tab4=new VerticalLayout();
		tab4.addComponents(room_modify);
		tabsheet.addTab(tab4,"Room Info");
		
		VerticalLayout tab5=new VerticalLayout();
//		tab5.addComponents();
		tabsheet.addTab(tab5,"Items Info");
		
		VerticalLayout tab6=new VerticalLayout();
//		tab6.addComponents();
		tabsheet.addTab(tab6,"Charges");
		addComponent(tabsheet);
	}

	private void eventProcess(){	
		//event
		evt_modify.addClickListener(e->{
			MyUI.navigateTo("newinfo");
		});
		//room
		room_modify.addClickListener(e->{
			MyUI.navigateTo("reservation");
		});
	}

	private void dataProcess() {
		//customer info filled
		int custid=(int)VaadinService.getCurrentRequest().getWrappedSession().getAttribute("custid");
		System.out.println(custid);
		CustDataService service = new CustDataService(custid);
		service.getData();
		cname.setValue(service.u.getCustomer());
		phone.setValue(service.u.getPhone());
		fax.setValue(service.u.getFax());
		add1.setValue(service.u.getAdd1());
		add2.setValue(service.u.getAdd2());
		city.setValue(service.u.getCity());
		state.setValue(service.u.getState());
		zip.setValue(service.u.getZip());
		country.setValue(service.u.getCountry());
		add1b.setValue(service.u.getBadd1());
		add2b.setValue(service.u.getBadd2());
		cityb.setValue(service.u.getBcity());
		stateb.setValue(service.u.getBstate());
		zipb.setValue(service.u.getBzip());
		countryb.setValue(service.u.getBcountry());
		mail.setValue(service.u.getMail());
		//evt
	
		
	}
	
//	public String filter(Object s) {
//		if (s.equals(null))
//			return "";
//		return s.toString();
//	}
	@Override
	public void enter(ViewChangeEvent event) {
	}
}
