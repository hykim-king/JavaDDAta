package recommender;

import InOutProject.Pocket;
import java.util.Scanner;

public class SkinRecommender {

    public void recommend(Pocket pocket) {  // Pocket 객체 전달
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== 피부 타입 추천 시스템 ===");
        System.out.println("1. 건성");
        System.out.println("2. 지성");
        System.out.println("3. 복합성");
        System.out.print("당신의 피부 타입을 선택하세요: ");

        int skinchoice = sc.nextInt();
        sc.nextLine();

        switch(skinchoice) {
            case 1: new DrySkin().showRecommendation(pocket); break;
            case 2: new OilySkin().showRecommendation(pocket); break;
            case 3: new ComplexSkin().showRecommendation(pocket); break;
            default: System.out.println("잘못된 입력입니다.");
        }
    }
}
