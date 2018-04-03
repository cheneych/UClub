package raymond.Test;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

import raymond.TestDetails.*;
import raymond.TestHomePage.*;
import raymond.TestReserve.*;


@SuppressWarnings("serial")
public class RaymondNavigatorViewProvider implements ViewProvider {

	@Override
	public String getViewName(String viewAndParameters) {
		return viewAndParameters;
	}

	@Override
	public View getView(String viewName) {
		// TODO Auto-generated method stub
		switch (viewName) {
		case "reservation" :
			return new ReservationView();
		case "home":
			return new HomeView();
		case "details":
			return new DetailsView();
		default:
			return null;
		}
	}

}
