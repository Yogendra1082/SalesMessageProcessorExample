package message.processor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Yogendra N. Date 27-03-2018 Class Name: AdjustPrice
 *
 * Performs Add, Subtract and Multiply adjustment operations for a given
 * product.
 *
 */

public class AdjustPrice {

	// adjustedPrice holds the adjusted price value
	private double adjustedPrice;

	public double getAdjustedPrice() {
		return adjustedPrice;
	}

	public void setAdjustedPrice(double adjustedPrice) {
		this.adjustedPrice = adjustedPrice;
	}

	// product holds the Product object.
	private Product product;
	private String adjustmentOperator;

	public String getAdjustmentOperator() {
		return adjustmentOperator;
	}

	public void setAdjustmentOperator(String adjustmentOperator) {
		this.adjustmentOperator = adjustmentOperator;
	}

	// Constructor takes Product as argument.
	public AdjustPrice(Product product) {
		this.product = product;
		this.adjustedPrice = 0.0;
	}

	/*
	 * Performs a reflection method call based on the adjustment operator
	 * requested e.g, add, subtract, multiply. Calling public methods is fine
	 * here else setAccessible should be set to true for private methods.
	 * 
	 * @returns adjusted price value
	 */
	public double calAdjustedPrice() {
		String adjustmentMethod = String.format("%sPrice", product.getAdjustmentOperator().toLowerCase());
		try {
			Method method = this.getClass().getMethod(adjustmentMethod, null);
			method.invoke(this, null);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return adjustedPrice;
	}

	// void transaction. Adds product totalprice with the requested price value.
	public void addPrice() {
		System.out.println(" inside Add");
		this.adjustedPrice = this.product.getTotalPrice()
				+ (this.product.getTotalQuantity() * this.product.getAdjustedPrice());
	}

	// void transaction. Subtracts product totalprice with the requested price
	// value.
	public void subPrice() {
		System.out.println(" inside Sub");
		this.adjustedPrice = this.product.getTotalPrice()
				- (this.product.getTotalQuantity() * this.product.getAdjustedPrice());
	}

	// void transaction. Multiplies product total price and quantity with the
	// requested price and appends to existing total value.
	public void multiplyPrice() {
		this.adjustedPrice = this.product.getTotalPrice()
				+ (this.product.getTotalPrice() * this.product.getAdjustedPrice())
				+ (this.product.getTotalQuantity() * this.product.getAdjustedPrice());
	}

	// @returns [String] e.g "Performed Add 20p to 21 apples and price adjusted
	// from 2.10p to 6.30p"
	public String adjustmentReport() {
		String adjustmentReport = String.format("Performed %s %.2fp to %d %s and price adjusted from %.2fp to %.2fp",
				this.product.getAdjustmentOperator(), this.product.getProductPrice(), this.product.getTotalQuantity(),
				this.product.getProductType(), this.product.getTotalPrice(), this.adjustedPrice);
		return adjustmentReport;
	}

}