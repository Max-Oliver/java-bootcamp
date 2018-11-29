package Logica_Operativa;

import Materiales_Estudio.Materiales;
import Vestimenta.Producto;

public class Index {

	public static void main(String[] args) {
		// Obetener el Producto
		ProductosFactor ProdFact = (ProductosFactor) LogicChoiceFactory.getFactory("Productos");

		// obtener el primer producto
		Producto Prod_First = ProdFact.getProducto("Mochila");
		Prod_First.toString();

		Producto P_Second = ProdFact.getProducto("Calzado");
		P_Second.toString();

		Producto P_Third = ProdFact.getProducto("Tunica");
		P_Third.toString();

		MaterialesFact MatFact = (MaterialesFact) LogicChoiceFactory.getFactory("Materiales");

		Materiales Mat_first = MatFact.getMateriales("Boligrafo");
		Mat_first.toString();

		Materiales M_Second = MatFact.getMateriales("Cartuchera");
		M_Second.toString();

		Materiales M_Third = MatFact.getMateriales("Cuaderno");
		M_Third.toString();

	}
}