import static org.junit.Assert.*;


import java.io.IOException;
import java.util.Vector;

import org.junit.Test;

public class Testing {

	@Test(expected=IOException.class)
	public void iotest() throws IOException {
		OpenAndClear oacInstance=new OpenAndClearCSV();

        oacInstance.open(".\\src\\employees (1).csv");
	}
	
	@Test(expected=IOException.class)
	public void iotest2() throws IOException {
		OpenAndClear oacInstance=new OpenAndClearJSON();

        oacInstance.open(".\\src\\employees (1).json");
	}
	@Test
	public void testiocsv() throws IOException {
		OpenAndClear oacInstance=new OpenAndClearCSV();

        oacInstance.open(".\\src\\employees.csv");
	}
	
	@Test
	public void testioempty() throws IOException {
		OpenAndClear oacInstance=new OpenAndClearCSV();

        oacInstance.open(".\\src\\employees empty.csv");
	}
	
	@Test
	public void testiojson() throws IOException {
		OpenAndClear oacInstance=new OpenAndClearJSON();

        oacInstance.open(".\\src\\employees.json");
	}
	@Test(expected=NullPointerException.class)
	public void testclosingcsv()  throws NullPointerException,IOException {
		OpenAndClear oacInstance=new OpenAndClearCSV();
		oacInstance.closing();
	}
	@Test(expected=NullPointerException.class)
	public void testclosingjson()  throws NullPointerException,IOException {
		OpenAndClear oacInstance=new OpenAndClearJSON();
		oacInstance.closing();
	}
	
	@Test
	public void testvectorjson() throws IOException {
		OpenAndClearJSON oacInstance=new OpenAndClearJSON();
		Vector <String> vectorJSON;
		vectorJSON= oacInstance.getVectorOfLines();
		//length of json file 
		assertEquals(39,vectorJSON.size());
		
	}
	@Test
	public void testvectorcsv() throws IOException {
		OpenAndClearCSV oacInstance=new OpenAndClearCSV();
		Vector <String[]> vectorCSV;
		vectorCSV= oacInstance.getVectorOfLines();
		//length of csv file 
		assertEquals(6,vectorCSV.size());
	}
	
	@Test
	public void testhashmapJSON() throws IOException {
		OpenAndClearJSON oacInstance=new OpenAndClearJSON();
		Vector <String> vectorJSON;
		vectorJSON= oacInstance.getVectorOfLines();
		ProfessionsJSON profInstance = new ProfessionsJSON();
		assertNull(profInstance.getMap());
		profInstance.setProfessions(vectorJSON);
		assertNotNull(profInstance.getMap());
	}
	@Test
	public void testhashmapCSV() throws IOException {
		OpenAndClearCSV oacInstance=new OpenAndClearCSV();
		Vector <String[]> vectorCSV;
		vectorCSV= oacInstance.getVectorOfLines();
		ProfessionsCSV profInstance = new ProfessionsCSV();
		assertNull(profInstance.getMap());
		profInstance.setProfessions(vectorCSV);
		assertNotNull(profInstance.getMap());
	}
	
	@Test
	public void testresultJSON() throws IOException {
		OpenAndClearJSON oacInstance=new OpenAndClearJSON();
		Vector <String> vectorJSON;
		vectorJSON= oacInstance.getVectorOfLines();
		ProfessionsJSON profInstance = new ProfessionsJSON();
		profInstance.setProfessions(vectorJSON);
		//with the dots! shouuld be removed

		assertEquals(20000.0,profInstance.getSalaryOf("Editor."),(0.1));
		assertEquals(10000.5,profInstance.getSalaryOf("Musician."),(0.1));
	}
	
	@Test
	public void testresultCSV() throws IOException {
		OpenAndClearCSV oacInstance=new OpenAndClearCSV();
		Vector <String[]> vectorCSV;
		vectorCSV= oacInstance.getVectorOfLines();
		ProfessionsCSV profInstance = new ProfessionsCSV();
		profInstance.setProfessions(vectorCSV);
		//without the dots!

		assertEquals(20000.0,profInstance.getSalaryOf("Editor"),(0.1));
		assertEquals(10000.5,profInstance.getSalaryOf("Musician"),(0.1));
		
	}

}
