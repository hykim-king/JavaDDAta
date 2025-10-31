package recommender;

import InOutProject.Pocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OilySkin {

    private List<String> products;
    private List<Integer> price;

    public OilySkin() {
        products = new ArrayList<>();
        price = new ArrayList<>();
        
        products.add("라로슈포제 에빠끌라 토너_살리실산 함유 / 모공 케어 / 피지 조절");
        price.add(18000);
        products.add("비오템 블루 테라피 토너_해조 추출물 함유 / 유분 조절 / 피부 진정");
        price.add(15000);
        products.add("클리니크 드라마티컬리 디퍼런트 모이스춰라이징 젤_가벼운 젤 타입 / 유분 없이 보습");
        price.add(11000);
        products.add("더페이스샵 티트리 모이스처 로션_티트리 추출물 함유 / 피부 진정 / 유분 관리");
        price.add(11000);
    }

    public void showRecommendation(Pocket pocket) {
        Scanner sc = new Scanner(System.in);

        System.out.println("┌─────────────────────────────────────────────────────────────────────────┐");
        System.out.println(" === 지성 피부 추천 제품 ===");
        for(int i = 0; i < products.size(); i++) {
            System.out.println(" " +(i+1) + ". " + products.get(i)+ " (" + price.get(i) + "원)");
        }

        System.out.println("└─────────────────────────────────────────────────────────────────────────┘");
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
