package raymond.TestDetails;

import com.vaadin.data.Binder;
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

public class ItemsForm extends FormLayout {
	private TextField name=new TextField("Name");
	private TextField price=new TextField("Price");
	private TextField Qty=new TextField("Qty");
	private TextField total=new TextField("Total");

	private Button save=new Button("Save");

	private Items item;

	private DetailsView dv;

	private Binder<Items> binder=new Binder<>(Items.class);

	public ItemsForm(DetailsView dv) {
		this.dv = dv;
		
		setSizeUndefined();
		
		name.setReadOnly(true);
		price.setReadOnly(true);
		total.setReadOnly(true);
		
		addComponents(name,price,Qty,total,save);
		
		binderInit();
		binder.bindInstanceFields(this); 
		
		save.addClickListener(e->save());
		save.setClickShortcut(KeyCode.ENTER);
	}
	
	public void setItems(Items item) {
		this.item = item;
		binder.setBean(item);
		setVisible(true);
	}
	public void save() {
		Double dPrice=Double.parseDouble(this.price.getValue());
		int dQty=Integer.parseInt(this.Qty.getValue());
		Double sum=dPrice*dQty;
		this.total.setValue(String.valueOf(sum));
		dv.update();
	}

	public void binderInit() {
//		binder.forField(price)
//		.withConverter(
//				new StringToDoubleConverter(""))
//		.bind(Items::getPrice, Items::setPrice);
//
//		binder.forField(Qty)
//		.withConverter(
//				new StringToIntegerConverter(""))
//		.bind(Items::getQty, Items::setQty);
//
//		binder.forField(total)
//		.withConverter(
//				new StringToDoubleConverter(""))
//		.bind(Items::getTotal, Items::setTotal);
	}
}