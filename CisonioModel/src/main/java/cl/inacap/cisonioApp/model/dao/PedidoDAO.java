package cl.inacap.cisonioApp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.ZoneId;
import java.sql.Date;

import cl.inacap.cisonioApp.model.dto.productos.Producto;
import cl.inacap.cisonioApp.model.utils.BDUtils;

public class PedidoDAO {
	private BDUtils bu = new BDUtils();
	
	public void add(Producto p) {
		try {
			this.bu.conectar();
			Connection con = this.bu.getConexion();
			String sql = "INSERT INTO producto(fechaCompra, fechaVencimiento, precioCompra, codigo)"
			+ " VALUES(?, ?, ? , ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setDate(1, Date.valueOf(p.getFechaCompra()));
			st.setDate(2, Date.valueOf(p.getFechaVencimiento()));
			st.setInt(3, p.getPrecio());
			st.setInt(4, p.getDescripcion().getCodigo());
			
			st.executeUpdate();
		}catch(Exception ex) {
			System.out.println(ex);
		}finally {
			this.bu.desconnectar();
		}
	}
}
