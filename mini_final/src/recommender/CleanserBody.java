package recommender;

import InOutProject.Pocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CleanserBody {

	private List<String> products;
	private List<Long> price;

	public CleanserBody() {
		products = new ArrayList<>();
		price = new ArrayList<>();

		products.add("유세린 딥모이스처 바디워시_글리세린 함유 / 보습 세정 / 피부 건조 예방");
		price.add(18000L);
		products.add("라로슈포제 리피듀얼 바디워시_세라마이드 함유 / 피부 장벽 보호 / 민감 피부 적합");
		price.add(19000L);
		products.add("더페이스샵 슈가 스크럽_설탕 알갱이 / 각질 제거 / 피부 결 매끄럽게");
		price.add(20000L);
		products.add("앤아더 스토리즈 펄 드 코코 글로우 바디스크럽");
		price.add(17000L);
		products.add("스크럽앤케어 코코넛 스크럽_코코넛 오일 함유 / 각질 제거 + 보습");
		price.add(16000L);

	}

	public void showRecommendation(Pocket pocket) {
		Scanner sc = new Scanner(System.in);

		System.out.println("\n=== 바디 클렌저 제품 추천 ===");
		for (int i = 0; i < products.size(); i++) {
			 System.out.println((i+1) + ". " + products.get(i) + " (" + price.get(i) + "원)");
		}
		

		System.out.print("담고 싶은 제품 번호를 입력하세요: ");
		int choice = sc.nextInt();
		sc.nextLine();

		if (choice > 0 && choice <= products.size()) {
			 pocket.addItem(products.get(choice - 1), price.get(choice - 1));
		} else {
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
			showRecommendation(pocket);
		}
	}
}
