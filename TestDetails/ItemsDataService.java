package raymond.TestDetails;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

import com.vaadin.data.Result;

import raymond.TestDB.DataService;
import raymond.TestDB.Pools;
import raymond.TestDB.Pools.Names;
import raymond.TestReserve.Room;

public class ItemsDataService extends DataService<Items> {
	public static ArrayList<Items> ItemList = new ArrayList<Items>();
	public ItemsDataService(int id) {
		super(Pools.getConnectionPool(Names.RAYMOND));
		//System.out.println("enter ser");
		String sqlStr = "select servitemid,servtype,servmenutype,servitemname,servitemchrg,servitemcost,servsu,inventoried,taxid,quantitydefault,itemflag,webavail,servitemorder from servsetup where servtype="+id;
		ItemList.clear();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sqlStr)) {
				try (ResultSet rs = stmt.executeQuery()) {
					int i=0;
					while (rs.next()) {
						i=1;
						Items c=new Items();
						c.setServitemid(getInteger(rs, i++));
						c.setServtype(getInteger(rs,i++));
						c.setServmenutype(getInteger(rs, i++));
						c.setServitemname(getString(rs,i++));
						c.setServitemchrg(getBigDecimal(rs,i++));
						c.setServitemcost(getBigDecimal(rs,i++));
						c.setServsu(getBigDecimal(rs,i++));
						c.setInventoried(getString(rs,i++));
						c.setTaxid(getInteger(rs,i++));
						c.setQuantitydefault(getInteger(rs,i++));
						c.setItemflag(getInteger(rs,i++));
						c.setWebavail(getString(rs,i++));
						c.setServitemorder(getInteger(rs,i++));
						ItemList.add(c);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Unable to fetch results", e);
		}
	}

	@Override
	public Items getRow(ResultSet rs) throws SQLException {
		return null;
	}

	protected Result<Items> get(Connection conn, String ItemsId) throws SQLException {

		try (PreparedStatement stmt = conn.prepareStatement("select * from EVENT where EVTID = 33600")) {
			setString(stmt, 1, ItemsId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return Result.ok(getRow(rs));
				}
			}
		}

		return Result.error("Could not get Items record for id " + ItemsId);

	}

	public Result<Items> get(String ItemsId) {

		try (Connection conn = dataSource.getConnection()) {
			return get(conn, ItemsId);
		} catch (SQLException e) {
			logger.error("Could not get record {}", ItemsId);
		}

		return Result.error("Could not get Items record for id " + ItemsId);

	}

	public Collection<Items> getAll() {

		ArrayList<Items> list = new ArrayList<Items>();
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

	public String storeRow(Connection conn, Items row) throws SQLException {

		try (CallableStatement call = conn.prepareCall("{ ? = call core.Items(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }")) {

			int x = 1;
			call.registerOutParameter(x++, Types.VARCHAR);

//			setTimestamp(call, x++, row.getDay());
//			setString(call,x++,row.getCustName());
//			setString(call, x++, row.getEvtName());

			call.executeUpdate();
			return call.getString(1);
		}

	}

	public Result<Items> storeRow(Items row) throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			String s = storeRow(conn, row);
			conn.commit();
			return get(conn, s);
		}
	}
}

