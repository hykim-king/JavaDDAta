package recommender;

import InOutProject.Pocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ComplexSkin {

    private List<String> products;
    private List<Long> price;

    public ComplexSkin() {
        products = new ArrayList<>();
        price = new ArrayList<>();
        
        products.add("미샤 타임레볼루션 화이트 토너_발효 성분 함유 / 수분 + 진정 / 피부결 개선");
        price.add(14000L);
        products.add("설화수 윤조 에센스 토너_진세노사이드 함유 / 보습 + 영양 공급 / 피부 톤 개선");
        price.add(21000L);
        products.add("헤라 UV 미스트 로션_가벼운 젤 타입 / 수분 공급 / 산뜻한 마무리");
        price.add(22000L);
        products.add("닥터자르트 시카페어 젤 로션_판테놀 & 병풀 추출물 함유 / 진정 + 수분");
        price.add(24000L);
    
    }

    public void showRecommendation(Pocket pocket) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== 복합성 피부 추천 제품 ===");
        for(int i = 0; i < products.size(); i++) {
        	if(i < price.size()) {
            System.out.println((i+1) + ". " + products.get(i) + " (" + price.get(i) + "원)");
        }
        }

        System.out.print("담고 싶은 제품 번호를 입력하세요: ");
        int choice = sc.nextInt();
        sc.nextLine();

        if(choice > 0 && choice <= products.size()&& choice <= price.size()) {
        	pocket.addItem(products.get(choice - 1), price.get(choice - 1));
        } else {
            System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            showRecommendation(pocket);
        }
    }
}
