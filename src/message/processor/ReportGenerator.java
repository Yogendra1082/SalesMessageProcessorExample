package message.processor;

/**
 * Created by Yogendra N.
 * Date 27-03-2018
 * Class Name: Product
 *
 *     Helper class to generate reports
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ReportGenerator {

	private List<String> lineItems = new ArrayList<String>();

	public List<String> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<String> lineItems) {
		this.lineItems = lineItems;
	}

	public void generateNormalReport(HashMap<String, Product> map) {

		System.out.println("10 sales appended to log");
		System.out.println("*************** Log Report *****************");
		System.out.println("|Product           |Quantity   |Value      |");
		for (Entry<String, Product> entry : map.entrySet()) {
			formatReports(entry.getKey(), entry.getValue());

		}
		try {
			// Add 2 second pause
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void formatReports(String type, Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(),
				product.getTotalPrice());
		System.out.println(lineItem);
	}

	public void generateAdjustmentReport(HashMap<String, Product> map) {

		System.out.println(
				"Application reached 50 messages and cannot process further. The following are the adjustment records made;\n");
		System.out.println("50  sales appended to log");
		System.out.println("***************Adjustment Log Report *****************");
		System.out.println("|Product           |Adjustment Operator   |Adjusted Price      | Total Value");
		for (Entry<String, Product> entry : map.entrySet()) {
			formatAdjustmentReports(entry.getKey(), entry.getValue());

		}
		System.exit(0);
	}

	public void formatAdjustmentReports(String type, Product product) {
		for (AdjustPrice temp : product.getListAdjustPrice()) {
			String lineItem = String.format("|%-18s|%-18s|%-11.2f|%-11.2f|", product.getProductType(),
					temp.getAdjustmentOperator(), temp.getAdjustedPrice(), product.getTotalPrice());
			System.out.println(lineItem);
		}

	}
}
