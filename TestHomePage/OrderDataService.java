package raymond.TestHomePage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Result;

import raymond.TestDB.DataService;
import raymond.TestDB.Pools;
import raymond.TestDB.Pools.Names;

public class OrderDataService extends DataService<Order> {

	public OrderDataService() {
		super(Pools.getConnectionPool(Names.RAYMOND));
		System.out.println("enter ser");
		sqlQuery = "select evtstart2 as day, evtstart2 as starttime, evtend2 as endtime, evtname as name from EVENT ";
	}

	@Override
	public Order getRow(ResultSet rs) throws SQLException {
		System.out.println("Getting row");
		Order u = new Order();
		int i = 1;

		u.setDay(getLocalDate(rs, i++));
		u.setStartTime(getLocalTime(rs, i++));
		u.setEndTime(getLocalTime(rs, i++));
		u.setEvtName(getString(rs, i++));
		//u.setRoom(getString(rs, i++));

		return u;

	}

	protected Result<Order> get(Connection conn, String OrderId) throws SQLException {

		try (PreparedStatement stmt = conn.prepareStatement("select * from EVENT where EVTID = 33600")) {
			setString(stmt, 1, OrderId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return Result.ok(getRow(rs));
				}
			}
		}

		return Result.error("Could not get Order record for id " + OrderId);

	}

	public Result<Order> get(String OrderId) {

		try (Connection conn = dataSource.getConnection()) {
			return get(conn, OrderId);
		} catch (SQLException e) {
			logger.error("Could not get record {}", OrderId);
		}

		return Result.error("Could not get Order record for id " + OrderId);

	}

	public Collection<Order> getAll() {

		ArrayList<Order> list = new ArrayList<Order>();
		try (Connection conn = dataSource.getConnection()) {
			try(PreparedStatement stmt = conn.prepareStatement(sqlQuery)) {
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						list.add(getRow(rs));
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public String storeRow(Connection conn, Order row) throws SQLException {

		try (CallableStatement call = conn.prepareCall("{ ? = call core.Order(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }")) {

			int x = 1;
			call.registerOutParameter(x++, Types.VARCHAR);

			setTimestamp(call, x++, row.getDay());
			setTimestamp(call, x++, row.getStartTime());
			setTimestamp(call, x++, row.getEndTime());
			setString(call, x++, row.getEvtName());
			//setString(call, x++, row.getRoom());

			call.executeUpdate();
			return call.getString(1);
		}

	}

	public Result<Order> storeRow(Order row) throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			String s = storeRow(conn, row);
			conn.commit();
			return get(conn, s);
		}
	}
}
