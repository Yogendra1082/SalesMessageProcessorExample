package message.processor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogendra N. Date 27-03-2018 Class Name: Product
 *
 * A product class contains the product details such as it's type, price
 * quantity, and stores total price value and quantity of a product. Also stores
 * the last performed adjustment operation such as Add, Subtract and Multiply.
 */
public class Product {

	private double productPrice;

	private int productQuantity;

	private String adjustmentOperator;

	private String productType;

	private int totalQuantity;

	private double totalPrice;
	private double adjustedPrice;

	private List<AdjustPrice> listAdjustPrice = new ArrayList<AdjustPrice>();

	public List<AdjustPrice> getListAdjustPrice() {
		return listAdjustPrice;
	}

	public void setListAdjustPrice(List<AdjustPrice> listAdjustPrice) {
		this.listAdjustPrice = listAdjustPrice;
	}

	public double getAdjustedPrice() {
		return adjustedPrice;
	}

	public void setAdjustedPrice(double adjustedPrice) {
		this.adjustedPrice = adjustedPrice;
	}

	// Constructor
	public Product(String type) {
		this.totalPrice = 0.0;
		this.totalQuantity = 0;
		this.productType = type;
		this.adjustmentOperator = null;
	}

	// Calculate the given quantity with given price and return the value
	public double calculatePrice(int productQuantity, double productPrice) {
		return productQuantity * productPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void appendTotalPrice(double productPrice) {
		this.totalPrice += productPrice;
	}

	public void setTotalQuantity(int quantity) {
		this.totalQuantity += quantity;
	}

	public int getTotalQuantity() {
		return this.totalQuantity;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String type) {
		this.productType = type;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getAdjustmentOperator() {
		return adjustmentOperator;
	}

	public void setAdjustmentOperator(String adjustmentOperator) {
		this.adjustmentOperator = adjustmentOperator;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product [productType=");
		builder.append(productType);
		builder.append(", productPrice=");
		builder.append(productPrice);
		builder.append(", productQuantity=");
		builder.append(productQuantity);
		builder.append(", adjustmentOperator=");
		builder.append(adjustmentOperator);
		builder.append(", adjustedPrice=");
		builder.append(adjustedPrice);
		builder.append(", totalPrice=");
		builder.append(totalPrice);
		builder.append("]\n");
		return builder.toString();
	}

}
