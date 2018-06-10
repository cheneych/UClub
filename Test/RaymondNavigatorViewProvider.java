package raymond.Test;

import java.sql.SQLException;

import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;
import raymond.Login.LoginView;
import raymond.MoreInfo.moreInfoView;
import raymond.TestDetails.*;
import raymond.TestHomePage.*;
import raymond.TestInfo.InfoView;
import raymond.TestReserve.*;


@SuppressWarnings("serial")
public class RaymondNavigatorViewProvider implements ViewProvider {

	final transient Logger logger = LoggerFactory.getLogger(RaymondNavigatorViewProvider.class);
	
	@Override
	public String getViewName(String viewAndParameters) {
		
		logger.error("viewAndParameters {}",viewAndParameters);
		if(viewAndParameters.indexOf("/")>0) {
			return viewAndParameters.substring(0, viewAndParameters.indexOf("/"));
		} else {
			return viewAndParameters;
		}
		
	}

	@Override
	public View getView(String viewName) {
		
		/* if(User.getUser()==null) {
			return new LoginView();
		} */
		
		// TODO Auto-generated method stub
		switch (getViewName(viewName)) {
		case "reservation" :
			return new ReservationView();
		case "home":
			return new HomeView();
		case "details":
			return new DetailsView();
		case "login":
			return new LoginView();
		case "info":
			return new InfoView();
		case "newinfo":
			return new moreInfoView();
		default:
			logger.error("Unable to determine view");
			return null;
		}
	}

}
