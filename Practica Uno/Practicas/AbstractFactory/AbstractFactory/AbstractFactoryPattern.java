package AbstractFactory;

public class AbstractFactoryPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//get Shape factory
		AbstractFactory shapeFactory = FactoryProducer.getFactory("Shape");
		
		//get el objeto de shape circulo
		Shape shape1 = shapeFactory.getShape("Circle");
		
		//call draw methos of shape of circle
		shape1.draw();
		
		//get an object of shape rectangle
		Shape shape2 = shapeFactory.getShape("Rectangle");
		
		//call draw etjps pf sjaè rectamgle
		shape2.draw();
		
		// objet 3
		Shape shape3 = shapeFactory.getShape("Square");
		
		//call draw
		shape3.draw();
		
		AbstractFactory colorFactory = FactoryProducer.getFactory("Color");
		
		Color c1 = colorFactory.getColor("RED");
		c1.fill();		
		
		Color c2 = colorFactory.getColor("GREEN");
		c2.fill();
		
		Color c3 = colorFactory.getColor("BLUE");
		c3.fill();		
				
				
				
				
				
	}

}
