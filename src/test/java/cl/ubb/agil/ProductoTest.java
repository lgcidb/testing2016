package cl.ubb.agil;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ProductoTest {
	Producto p;
	Categoria c;
	Existencia e;
	
	@Test
	public void bajoStockEsVerdaderoConStockMinIgualA20YStockIgualA19(){
		
		/*Arrange*/
		p= new Producto("Bajo Tenson", 20, 2000);
		p.stock=19;
		/*Act*/
		boolean comparaStock = p.isBajoStock();
		/*Assert*/
		assertTrue(comparaStock);
	
	}
	
	@Test
	public void bajoStockEsFalsoConStockMinimoIgualA10YStockIgualA11(){
		
		/*Arrange*/
		p = new Producto("Piano", 10, 500);
		p.stock = 11;
		/*Act*/
		boolean comparaStock = p.isBajoStock();
		/*Assert*/
		assertFalse(comparaStock);
	}
	
	@Test
	public void noEsPosibleAgregarExistencia(){
		
		/*Arrange*/
	    p = new Producto("Guitarra electrica Tenson", 5, 50);
	    p.stock = 51;
		e = new Existencia("K-300");
		
		/*Act*/
		boolean agregaExistencia = p.addExistencia(e);
		
		/*Act*/
		assertFalse(agregaExistencia);
	}
	
	@Test
	public void agregarExistenciaExitosamente(){
		
		/*Arrange*/
		p = new Producto("Piano Essex", 5, 20);
		p.stock = 19;
		e = new Existencia("P-Essex 20");
		
		/*Act*/
		boolean agregaExistencia = p.addExistencia(e);
		
		/*Act*/
		assertTrue(agregaExistencia);
	}
	@Test (expected=ExcepcionDeProducto.class)
	public void LanzaExcepcionDeProductoEnObtenerProximaExistencia() throws ExcepcionDeProducto{
		
		/*Arrange*/
		Producto p = new Producto("Piano Essex", 5, 20);
		p.stock = 0;
		
		/*Act*/
		e = p.getProximaExistencia();
	}
	
	@Test
	public void obtieneProximaExistenciaCorrectamente() throws ExcepcionDeProducto{
		
		/*Arrange*/
		p = new Producto("Piano Yamaha", 10, 50);
		p.stock = 15;
		e = new Existencia("P-Yamaha 15");
		p.existencias[(p.stock - 1)] = e;
		Existencia exis = new Existencia("Guit 35");
		p.existencias[(p.stock - 2)] = exis;
		
		/*Act*/
		Existencia ex = p.getProximaExistencia();
		
		/*Assert*/
		assertEquals(e, ex);
		
	}
	
	@Test(expected=ExcepcionDeProducto.class)
	public void obtenerCodigoLanzaExcepcion() throws ExcepcionDeProducto{
		
		/*Arrange*/
		c = new Categoria("Guitarras electricas", "GE300");
		p = new Producto("Guitarra Ibanez", 5, 20, c);
		p.stock = 0;
		
		/*Act*/
		String cod = p.getCodigoProximaExistencia();
	}
	
	@Test
	public void obtenerCodigoProximaExistenciaCorrectamente() throws ExcepcionDeProducto{
		
		/*Arrange*/
		c = new Categoria("Guitarras electricas", "GE300");
		p = new Producto("Guitarra Ibanez", 5, 20, c);
		p.stock = 6;
		e = new Existencia("50");
		p.existencias[(p.stock - 1)] = e;
		
		/*Act*/
		String retornoMetodo = p.getCodigoProximaExistencia();
		String cod = "GE300" + "-"+"50";
		boolean comparaStrings;
		if(cod.equalsIgnoreCase(retornoMetodo)==true){
			comparaStrings = true;
		}else{
			comparaStrings = false;
		}
		
		/*Assert*/
		assertTrue(comparaStrings);
	}
	
	@Test
	public void comparaCodigoConUnoDistintoLanzaError() throws ExcepcionDeProducto{
		/*Arrange*/
		c = new Categoria("Guitarras electricas", "GE300");
		p = new Producto("Guitarra Ibanez", 5, 20, c);
		p.stock = 6;
		e = new Existencia("50");
		p.existencias[(p.stock - 1)] = e;
		
		/*Act*/
		String retornoMetodo = p.getCodigoProximaExistencia();
		String cod = "GE300" + "-"+"550";
		boolean comparaStrings;
		if(cod.equalsIgnoreCase(retornoMetodo)==true){
			comparaStrings = true;
		}else{
			comparaStrings = false;
		}
		
		/*Assert*/
		assertTrue(comparaStrings);
	}
	
	
	
	
	
	
}
