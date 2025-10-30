package Payment;

import InOutProject.Pocket;

import java.security.PublicKey;
import java.util.*;

public class Payment {

	public static void main(String[] args) {

		Pocket pocket = new Pocket();


		pocket.showItems();
		
		checkout(pocket);

	}
		 public static void checkout(Pocket cart) {
		if (cart.isEmpty()) {
			System.out.println("!결제할 상품이 없습니다!");
			return;
		}

		cart.showItems();
		
		long totalWon = cart.total();
		System.out.println("총 결제 금액(원): " + totalWon + "원");

		// double usdRate = api.getUsdKrwRate();

		// double totalUsd = totalWon / usdRate;

		// System.out.printf("총 결제 금액(USD): %.2f달러\\n", totalUsd);

		System.out.println("결제 완료되었습니다!");
		cart.clear();
	}

//	public static void checkout(Pocket cart) {
//		// TODO Auto-generated method stub
		
	}


