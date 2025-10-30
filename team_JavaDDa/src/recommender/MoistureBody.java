package recommender;

import InOutProject.Pocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoistureBody{

    private List<String> products;
    private List<Long> price;

    public MoistureBody() {
        products = new ArrayList<>();
        price = new ArrayList<>();
        
        products.add("니베아 수퍼모이스처 로션:_글리세린 & 판테놀 함유 / 깊은 보습 / 피부 부드럽게");
        price.add(20000L);
        products.add("아벤느 수딩 바디로션_아벤느 온천수 함유 / 진정 + 수분 공급 / 민감 피부 적합");
        price.add(21000L);
        products.add("조말론 라임 바질 바디 오일_식물성 오일 함유 / 영양 공급 / 윤기");
        price.add(16000L);
        products.add("유세린 딥 모이스처 바디오일_세라마이드 & 글리세린 함유 / 보습 집중 / 건조 예방");
        price.add(19000L);
  
    }

    public void showRecommendation(Pocket pocket) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== 바디 보습 제품 추천 ===");
        for(int i = 0; i < products.size(); i++) {
            System.out.println((i+1) + ". " + products.get(i)+ " (" + price.get(i) + "원)");
        }

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

