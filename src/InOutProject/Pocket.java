package InOutProject;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Pocket {

	private List<String> items;
	private List<Integer> prices;

	public Pocket() {
		items = new ArrayList<>();
		prices = new ArrayList<>();
	}

	public void addItem(String product, int price) {
		items.add(product);
		prices.add(price);

		System.out.println("선택하신 ");
		int lastIndex = items.size() - 1;
		System.out.println("▶ " + items.get(lastIndex) + "_(" + prices.get(lastIndex) + "원)");
		System.out.println(" ");
		System.out.println("장바구니에 담겼습니다.");
	}

	public void showItems() {
		if (items.isEmpty()) {
			System.out.println("============================");
			System.out.println("  장바구니가 비어 있습니다.   ");
			System.out.println("============================");
			return;
		}
		System.out.println("=============== 장바구니 목록 ===============");

		int total = 0;
		for (int i = 0; i < items.size(); i++) {

			System.out.println("  " + (i + 1) + ". " + items.get(i) + " (" + prices.get(i) + "원)");
			total += prices.get(i);
		}

		double usdRate = getUsdKrwRate();
		double totalUsd = total * usdRate;

		System.out.println("--------------------------------------");
		System.out.printf("  ※ 총 금액: %,d원 (약 $%.2f USD, )\n", total, totalUsd); // 총금액 출력
		System.out.println("=========================================");
	}

	public void clear() {
		items.clear();
		System.out.println(" 장바구니가 비워졌습니다.");
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public int total() {
		int sum = 0;
		for (int price : prices) {
			sum += price;
		}
		return sum;
	}

	public static double getUsdKrwRate() {
		try {
			URL url = new URL("https://api.exchangerate.host/latest?base=KRW&symbols=USD");
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String json = br.lines().reduce("", (a, b) -> (a + b));
				// 모든 줄을 하나의 문자여롤 합침
				int i = json.indexOf("\"USD\":");
				if (i > 0) {
					String sub = json.substring(i + 6);
					// USD라는 글자 바로 뒤부터 끝까지 잘라냄
					String num = sub.split("[,}]")[0].trim();
					// 쉼표나 중괄호가 나오기 전까지만 숫자 부분을 잘라냄.그리고 혹시 앞뒤에 공백이 있으면 제거
					return Double.parseDouble(num);
					// 문자열 형태의 숫자("0.00073")를 double 실수형으로 반환
				}
			}
		} catch (Exception e) {
			System.out.println("[알림]환율 API 연결 실패 (오프라인 모드)");
		}
		return 0.00073;
	}
}
