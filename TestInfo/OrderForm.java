package raymond.TestInfo;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class OrderForm extends FormLayout {
	private TextField name=new TextField("Name");
	private TextField chrg=new TextField("Price");
	private TextField qty=new TextField("Qty");
	private TextField total=new TextField("Total");

	private Button update = new Button("Update");
	private Button drop = new Button("drop");

	private Orderitems item;

	private InfoView dv;

	private Binder<Orderitems> binder=new Binder<>(Orderitems.class);

	public OrderForm(InfoView dv) {
		this.dv = dv;
		
		setSizeUndefined();
		
		name.setReadOnly(true);
		chrg.setReadOnly(true);
		total.setReadOnly(true);
		HorizontalLayout h1=new HorizontalLayout();
		h1.addComponents(update,drop);
		addComponents(name,chrg,qty,total,h1);
		
		binderInit();
		binder.bindInstanceFields(this); 
		
		//handle event  need sql scripts
/*		update.addClickListener(e->save());
		drop.addClickListener(e->drop()); */
		//quick tab
//		update.setClickShortcut(KeyCode.ENTER);
	}
	
	public void setItems(Orderitems item) {
		this.item = item;
		binder.setBean(item);
		setVisible(true);
	}
	
	public void update() {
//		Double dPrice=Double.parseDouble(this.servitemchrg.getValue());
//		int dQty=Integer.parseInt(this.qty.getValue());
//		Double sum=dPrice*dQty;
//		this.total.setValue(String.valueOf(sum));
//		dv.update();
	}
	
	public void drop() {
		
	}

	public void binderInit() {
		binder.forField(name)
		.bind(Orderitems::getItem, Orderitems::setItem);

		
		binder.forField(chrg)
		.withConverter(
				new StringToDoubleConverter(""))
		.bind(Orderitems::getCharge, Orderitems::setCharge);

		binder.forField(qty)
		.withConverter(
				new StringToIntegerConverter(""))
		.bind(Orderitems::getQty, Orderitems::setQty);

		binder.forField(total)
		.withConverter(
				new StringToDoubleConverter(""))
		.bind(Orderitems::getTotal, Orderitems::setTotal);
	}
}