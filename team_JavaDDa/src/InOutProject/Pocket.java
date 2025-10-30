package InOutProject;

import java.util.ArrayList;
import java.util.List;

public class Pocket {

    private List<String> items;
    private List<Long>  prices;

    public Pocket() {
        items = new ArrayList<>();
        prices = new ArrayList<>();
    }

    public void addItem(String product, long price) {
        items.add(product);
        prices.add(price);
        System.out.println("선택하신 [" + items + "] (" + price + "원) 장바구니에 담겼습니다.");
    }

    public void showItems() {
        if(items.isEmpty()) {
        	System.out.println("┌───────────────────────┐");
            System.out.println("  장바구니가 비어 있습니다.   ");
            System.out.println("└───────────────────────┘");
            return;
        }
        System.out.println("┌───────── 장바구니 목록 ────────────┐");
        
        long total = 0;
        for(int i = 0; i < items.size(); i++) {
            System.out.println( "  "+(i+1) + ". " + items.get(i)+ " (" + prices.get(i) + "원)");
            total += prices.get(i);
        }
        System.out.println("------------------------------");
        System.out.println("  ※"+ "총 금액: " + total + "원"); // 총금액 출력
        System.out.println("└────────────────────────────────┘");
    }

    public void clear() {
        items.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public long total() {
		long sum = 0;
	    for(long price : prices) {
	        sum += price;
	    }
		return sum;
	}
}
