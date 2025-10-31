package recommender;

import InOutProject.Pocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DrySkin {

    private List<String> products;
    private List<Integer> price;

    public DrySkin() {
        products = new ArrayList<>();
        price = new ArrayList<>();
        
        products.add("네즈 크림스킨 토너:히알루론산 함유 / 촉촉한 보습형 / 피부결 개선");
        price.add(20000);
        products.add("키엘 칼렌듈라 토너_세라마이드 함유 / 꾸덕 제형 / 피부 당김 완화");
        price.add(13000);
        products.add("헤라 아쿠아볼릭 로션_히알루론산 & 글리세린 함유 / 수분 강화 / 피부결 개선");
        price.add(19000);
        products.add("닥터자르트 시카페어 로션_판테놀 함유 / 붉은기 완화 / 진정 보습");
        price.add(19000);
        
    }

    public void showRecommendation(Pocket pocket) {
        Scanner sc = new Scanner(System.in);

        System.out.println("┌─────────────────────────────────────────────────────────────────────┐");
		System.out.println("   === 건성 피부 추천 제품 ===");
        for(int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i)+ " (" + price.get(i) + "원)");
        }

        System.out.println("└─────────────────────────────────────────────────────────────────────┘");

		System.out.print("담고 싶은 제품 번호를 입력하세요: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice > 0 && choice <= products.size()) {
            pocket.addItem(products.get(choice - 1), price.get(choice - 1));
        } else {
            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            showRecommendation(pocket);
        }
    }
}
