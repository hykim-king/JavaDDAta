	package recommender;

	import InOutProject.Pocket;
	import java.util.Scanner;

	public class BodyRecommend {

	    public void recommend(Pocket pocket) {  // Pocket 객체 전달
	        Scanner sc = new Scanner(System.in);

	        System.out.println("┌───────────────────────┐");
	        System.out.println(" == 바디 타입 추천 시스템 ==");
	        System.out.println(" 1. 클렌저(바디워시와 스크럽)");
	        System.out.println(" 2. 보습(바디크림과 바디오일)");
	        System.out.println(" ───────────────────────");
	        System.out.println(" 원하는 바디 제품을 선택해주세요 ");
	        System.out.println("└────────────────────────┘");
	        System.out.print("[입력]:");

	        int skinchoice = sc.nextInt();
	        sc.nextLine();

	        switch(skinchoice) {
	            case 1: new CleanserBody().showRecommendation(pocket); break;
	            case 2: new MoistureBody().showRecommendation(pocket); break;
	            default: System.out.println("! 잘못된 입력입니다 !");
	        }
	    }
	}



