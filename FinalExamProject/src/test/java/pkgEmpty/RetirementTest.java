package pkgEmpty;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import javafx.scene.control.TextField;
import pkgCore.Retirement;
public class RetirementTest {

	@Test
	public void test() {
		Retirement people1 = new Retirement();
		people1.setdAnnualReturnWorking(0.07);
		people1.setdAnnualReturnRetired(0.02);
		people1.setdMonthlySSI(2642);
		people1.setdRequiredIncome(10000);
		people1.setiYearsRetired(20);
		people1.setiYearsToWork(40);
		double exceptPV = 1454485.55;
		assertEquals(people1.TotalAmountSaved(), exceptPV, 0.01);
		
		double exceptPMT = 554.13;
		assertEquals(exceptPMT, people1.AmountToSave(), 0.01);


		
		
	}

}
