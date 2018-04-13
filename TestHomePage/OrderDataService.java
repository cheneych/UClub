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

import TestDB.DataService;
import TestDB.Pools;
import TestDB.Pools.Names;

public class OrderDataService extends DataService<Order> {

	public OrderDataService() {
		super(Pools.getConnectionPool(Names.RAYMOND));
		sqlQuery = "select * from EVENT";
	}

	public Order getRow(ResultSet rs) throws SQLException {

		Order u = new Order();
		int i = 1;

		u.setDate(getString(rs, i++));
		u.setStartTime(getString(rs, i++));
		u.setEndTime(getString(rs, i++));
		u.setevtName(getString(rs, i++));
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

			setString(call, x++, row.getDate());
			setString(call, x++, row.getStartTime());
			setString(call, x++, row.getEndTime());
			setString(call, x++, row.getevtName());
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
