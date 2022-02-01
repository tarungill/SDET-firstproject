package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookTicket {
	
	@Test(dataProvider="dataProvider_bookTicketTest")
	public void bookTicketTest(String src,String dst)
	{
		System.out.println("Book Ticket from "+src+ "to" +dst);
	}
	
	@DataProvider
	public Object[][] dataProvider_bookTicketTest()
	{
		Object[][] objArr=new Object[5][2];
		
		objArr[0][0]="Bangalore";
		objArr[0][1]="Goa";
		
		objArr[1][0]="Bangalore";
		objArr[1][1]="mysore";
		
		objArr[2][0]="Bangalore";
		objArr[2][1]="mangalore";
		
		objArr[3][0]="Bangalore";
		objArr[3][1]="Hyd";
		
		objArr[4][0]="Bangalore";
		objArr[4][1]="Mp";
		return objArr;
	}

}
