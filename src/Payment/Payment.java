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
			System.out.println("! 결제할 상품이 없습니다 !");
			return;
		}

		cart.showItems();
		
		int totalWon = cart.total();
        double usdRate = Pocket.getUsdKrwRate();
        double totalUsd = totalWon * usdRate;
        System.out.println("┌───────────────────────────────────┐");
        System.out.printf(" 총 결제 금액: %,d원 (약 $%.2f USD)\n", totalWon, totalUsd);

		
		System.out.println("  결제 완료되었습니다!");
		cart.clear();
		System.out.println("  ※추가적으로 필요한게 있으실까요?");
		System.out.println("└───────────────────────────────────┘");
	}

		
	}


