package raymond.BetaReserve;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.simplecalendar.SimpleCalendar;
import raymond.Test.MyUI;
import raymond.Test.TopBarView;
import raymond.TestHomePage.Order;
import raymond.TestHomePage.OrderStatus;

@SuppressWarnings("serial")
@Theme("mytheme")
public class BetaResView extends TopBarView implements View {
	//Components
	public BetaResView() {
		init();
	}

	public void init() {
		eventProcess();
		dataProcess();
	}

	private void eventProcess(){
		

	}

	private void dataProcess() {
		
	}
}
