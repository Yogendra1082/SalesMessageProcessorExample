package message.processor;

/**
 * Created by Yogendra N.
 * Date 27-03-2018
 * Class Name: MessageProcessor
 *
 * Process the message based on Messagetype.
 *
 */
import java.util.HashMap;

public class MessageProcessor {
	public static HashMap<String, Product> map = new HashMap<String, Product>();
	public static ReportGenerator rp = new ReportGenerator();

	public void processMessage(String line) {
		String[] pair = line.split("\\|", -1);

		if (pair[0].toString().equalsIgnoreCase("MSGTYPE1")) {
			if (!map.containsKey(pair[1])) {
				Product p = new Product("");
				p.setProductType(pair[1]);
				p.setProductPrice(Double.parseDouble(pair[2]));
				map.put(pair[1], p);
			}

		} else if (pair[0].toString().equalsIgnoreCase("MSGTYPE2")) {
			Product p = map.get(pair[1]);
			if (p == null) {
				System.out.println(pair[1] + "- Wrong Product Type");
				return;
			}
			p.setProductQuantity(Integer.parseInt(pair[2]));
			p.setTotalQuantity(Integer.parseInt(pair[2]));
			setProductTotalPrice(p);
			map.put(pair[1], p);
		}

		else if (pair[0].toString().equalsIgnoreCase("MSGTYPE3")) {

			Product p = map.get(pair[1]);
			if (p == null) {
				System.out.println(pair[1] + "- Wrong Product Type");
				return;
			}
			p.setAdjustedPrice(Double.parseDouble(pair[2]));
			p.setAdjustmentOperator(pair[3]);
			setProductTotalPrice(p);
			map.put(pair[1], p);

		} else {
			System.out.println(" Wrong Message Type");
		}

		rp.getLineItems().add(pair[0].toString());

		if ((rp.getLineItems().size() % 10) == 0 && rp.getLineItems().size() != 0) {
			System.out.println(" Generate Report for 10th iterration");
			rp.generateNormalReport(map);

		}
		if ((rp.getLineItems().size() % 50) == 0 && rp.getLineItems().size() != 0) {
			rp.generateAdjustmentReport(map);
		}
	}

	private static void setProductTotalPrice(Product product) {

		double adjustedPrice;
		double productValue;

		if (product.getAdjustmentOperator() != null && !product.getAdjustmentOperator().isEmpty()) {
			AdjustPrice adjustPrice = new AdjustPrice(product);
			adjustedPrice = adjustPrice.calAdjustedPrice();
			product.getListAdjustPrice().add(adjustPrice);
			adjustPrice.setAdjustmentOperator(product.getAdjustmentOperator());
			// log.setAdjustmentReports(adjustPrice.adjustmentReport());
			System.out.println(" Adjustment Price-->" + adjustedPrice);
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
			product.appendTotalPrice(productValue);
		}
	}
}
