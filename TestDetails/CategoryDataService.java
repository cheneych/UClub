package raymond.TestDetails;

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

public class CategoryDataService extends DataService<Category> {
	public ArrayList<Category> CateList = new ArrayList<Category>();
	public CategoryDataService() {
		super(Pools.getConnectionPool(Names.RAYMOND));
		//System.out.println("enter ser");
		String sqlStr = "select headertypeid,headerdesc,taxid,servpictkey,webitem from headertypes";
//		CateList.clear();
		
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement stmt = conn.prepareStatement(sqlStr)) {
				try (ResultSet rs = stmt.executeQuery()) {
					int i=0;
					while (rs.next()) {
						i=1;
						Category c=new Category();
						c.setHeadertypeid(getInteger(rs, i++));
						c.setHeaderdesc(getString(rs,i++));
						c.setTaxid(getInteger(rs, i++));
						c.setServpictkey(getString(rs, i++));
						c.setWebitem(getString(rs, i++));
						CateList.add(c);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Unable to fetch results", e);
		}
	}

	@Override
	public Category getRow(ResultSet rs) throws SQLException {
		return null;
	}

	protected Result<Category> get(Connection conn, String CategoryId) throws SQLException {

		try (PreparedStatement stmt = conn.prepareStatement("select * from EVENT where EVTID = 33600")) {
			setString(stmt, 1, CategoryId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return Result.ok(getRow(rs));
				}
			}
		}

		return Result.error("Could not get Category record for id " + CategoryId);

	}

	public Result<Category> get(String CategoryId) {

		try (Connection conn = dataSource.getConnection()) {
			return get(conn, CategoryId);
		} catch (SQLException e) {
			logger.error("Could not get record {}", CategoryId);
		}

		return Result.error("Could not get Category record for id " + CategoryId);

	}

	public Collection<Category> getAll() {

		ArrayList<Category> list = new ArrayList<Category>();
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

	public String storeRow(Connection conn, Category row) throws SQLException {

		try (CallableStatement call = conn.prepareCall("{ ? = call core.Category(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }")) {

			int x = 1;
			call.registerOutParameter(x++, Types.VARCHAR);

//			setTimestamp(call, x++, row.getDay());
//			setString(call,x++,row.getCustName());
//			setString(call, x++, row.getEvtName());

			call.executeUpdate();
			return call.getString(1);
		}

	}

	public Result<Category> storeRow(Category row) throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			String s = storeRow(conn, row);
			conn.commit();
			return get(conn, s);
		}
	}
}

