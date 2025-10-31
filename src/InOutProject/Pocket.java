package InOutProject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class Pocket {

    private List<String> items;
    private List<Long> prices;

    public Pocket() {
        items = new ArrayList<>();
        prices = new ArrayList<>();
    }

    public void addItem(String product, long price) {
        items.add(product);
        prices.add(price);
        System.out.println("선택하신 [" + product + "] (" + price + "원) 장바구니에 담겼습니다.");
    }

    public void showItems() {
        if (items.isEmpty()) {
			System.out.println("============================");
			System.out.println("  장바구니가 비어 있습니다.   ");
			System.out.println("============================");
            return;
        }

		System.out.println("=============== 장바구니 목록 ===============");

        long total = 0;
        for (int i = 0; i < items.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + items.get(i) + " (" + prices.get(i) + "원)");
            total += prices.get(i);
        }

        double usdRate = getUsdKrwRate();
        double totalUsd = total * usdRate;

		System.out.println("--------------------------------------");
        System.out.printf("  ※ 총 금액: %,d원 (약 $%.2f USD)\n", total, totalUsd);
		System.out.println("=========================================");
    }

    public void clear() {
        items.clear();
        prices.clear();
        System.out.println("장바구니가 비워졌습니다.");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public long total() {
        long sum = 0;
        for (long price : prices) sum += price;
        return sum;
    }

    public List<String> getItems() {
        return items;
    }

    public List<Long> getPrices() {
        return prices;
    }

    public static double getUsdKrwRate() {
        try {
            URL url = new URL("https://api.exchangerate.host/latest?base=KRW&symbols=USD");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String json = br.lines().reduce("", (a, b) -> (a + b));
                int i = json.indexOf("\"USD\":");
                if (i > 0) {
                    String sub = json.substring(i + 6);
                    String num = sub.split("[,}]")[0].trim();
                    return Double.parseDouble(num);
                }
            }
        } catch (Exception e) {
            System.out.println("[알림] 환율 API 연결 실패 (오프라인 모드)");
        }
        return 0.00073;
    }
}
