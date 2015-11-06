package com.scicrop.tobbyescavador.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAdaptor extends DbConnector{
	private DbAdaptor(){}

	private static DbAdaptor INSTANCE = null;

	public static DbAdaptor getInstance(){
		if(INSTANCE == null) INSTANCE = new DbAdaptor();
		return INSTANCE;
	}
	public List<Polygon> getPolygon(){
		List<Polygon> lista = new ArrayList<Polygon>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT id FROM propriedades");
			rs = ps.executeQuery();
			while(rs.next()){
				Polygon p = new Polygon();
				p.setId(rs.getString("id"));
				System.out.println(p.getId());
				p.setPol(getLocation(rs.getInt("id")));
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				close(rs, ps, con);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;

	}
	
	public List<Location> getLocation(int id){
		List<Location> lista = new ArrayList<Location>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;

		try {

			con = getConnection();
			ps = con.prepareStatement("SELECT * FROM geometria WHERE propriedades_id = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				Location l = new Location();
				l.setLat(rs.getDouble("latitude"));
				l.setLon(rs.getDouble("longitude"));
				lista.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				close(rs, ps, con);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;

	}
}
