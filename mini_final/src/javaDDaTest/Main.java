package javaDDaTest;

import java.util.Scanner;
import InOutProject.InOut;
import InOutProject.MemberDao;
import InOutProject.MemberDTO;
import InOutProject.Pocket;
import Payment.Payment;
import recommender.BodyRecommend;
import recommender.SkinRecommender;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		InOut io = new InOut();
		MemberDao dao = new MemberDao();
		io.dao = dao; //경로 겹치지 않게 하기 위함
		Pocket pocket = new Pocket(); // 장바구니
		System.out.println("안녕하십니까 포도씨영입니다. 무엇을 도와드릴까요?");
		while (true) {
			System.out.println("┌─────────────────────────┐");
			System.out.println(" 원하는 항목을 선택해주세요");
			System.out.println(" 1. 로그인");
			System.out.println(" 2. 회원가입");
			System.out.println(" 0. 종료");
			System.out.println("└─────────────────────────┘");
			System.out.print(":");

//			if (!sc.hasNextLine())
//				break;
			String input = sc.nextLine();
			int choice;
			try {
				choice = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");
				continue;
			}

			if (choice == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}

			switch (choice) {
			case 1:
				io.login();
				break;
			case 2:
				io.signUp();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}

			if (io.currentMember != null) {
				boolean exitMenu = false;
				while (!exitMenu) {
					System.out.println("┌───────────────────────┐");
					System.out.println(" 1. 피부타입 추천 받기      ");
					System.out.println(" 2. 바디타입 추천 받기      ");
					System.out.println(" 3. 내 정보 확인하기        ");
					System.out.println(" 4. 장바구니 확인하기        ");
					System.out.println(" 5. 결제 하기              ");
					System.out.println(" 0. 종료하기                ");
					System.out.println("└───────────────────────┘");
//					if (!sc.hasNextLine())
//						break;
					input = sc.nextLine();
					int menu;
					try {
						menu = Integer.parseInt(input);
					} catch (Exception e) {
						System.out.println("숫자를 입력해주세요.\n");
						continue;
					}

					switch (menu) {
					case 1:
						new SkinRecommender().recommend(pocket);
						break;
					case 2:
						new BodyRecommend().recommend(pocket);
						break;
					case 3:
						System.out.println("확인할 내 정보를 선택해주세요");
						System.out.println("1. 이메일 및 이름 조회");
						System.out.println("2. 회원 삭제");
						System.out.print("선택: ");

						Scanner sc1 = new Scanner(System.in);
						int infochoice = sc1.nextInt(); // 실제 선택값 입력 받기

						switch (infochoice) {
						case 1:
							MemberDTO member = dao.findByEmail(io.currentMember.getEmail());
							if (member != null) {
								System.out.println("┌───────────────────────────────┐");
								System.out.println(" 이메일: " + member.getEmail());
								System.out.println(" 이 름: " + member.getName());
								System.out.println("└───────────────────────────────┘");
							} else {
								System.out.println("회원 정보를 찾을 수 없습니다.");
							}
							break;

						case 2:
							System.out.print("정말로 회원을 삭제하시겠습니까? (Y/N): ");
							sc1.nextLine(); // 버퍼 비우기
							String confirm = sc1.nextLine();

							if (confirm.equalsIgnoreCase("Y")) {
								boolean result = dao.deleteMember(io.currentMember.getEmail());
								if (result) {
									System.out.println("회원 삭제가 완료되었습니다.");
									io.currentMember = null; // 로그인 정보 초기화
									break;
								} else {
									System.out.println("회원 삭제에 실패했습니다.");
								}
							} else {
								System.out.println("회원 삭제가 취소되었습니다.");
							}
							break;

						default:
							System.out.println("잘못된 선택입니다.");
							break;
						}

						break; // case 3 종료
					case 4:
						pocket.showItems();
						break;

					case 5:
						Payment payment = new Payment();
						payment.checkout(pocket);
						break;

					case 0:
						System.out.println("프로그램 종료");
						exitMenu = true;
						io.currentMember = null;
						break;
					default:
						System.out.println("잘못된 입력입니다.");
					}
				}
			}
		}

		sc.close();
	}
}
