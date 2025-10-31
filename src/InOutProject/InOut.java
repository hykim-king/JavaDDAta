package InOutProject;

import java.util.Scanner;

public class InOut {
    private Scanner sc = new Scanner(System.in);
    public MemberDao dao = new MemberDao();
    public MemberDTO currentMember = null; // 로그인한 회원 저장

    public void signUp() {
    	System.out.println("=====================================================");
		System.out.println(" ______   _   ______    _    _      _   _   ____ ");
		System.out.println("|  ____| | |  |   ___| | |  | |    | | | | |  _ |");
		System.out.println("| |____  | |  |  | __  |  |_| |    | | | | | |_||");
		System.out.println("|____  | | |  |  ||  | |  _   |    | | | | |  __|");
		System.out.println(" ____| | | |  |  |_| | | | |  |    | |_| | | |    ");
		System.out.println("|______| |_|  |______| |_|  |_|    |_____| |_|    ");
		System.out.println("=====================================================");
        System.out.print("이메일 입력: ");
        String email = sc.nextLine();

        if (dao.findByEmail(email) != null) {
            System.out.println("이미 존재하는 이메일입니다.");
            return;
        }
        System.out.print("이름을 입력해주세요: ");
        String name = sc.nextLine();
        System.out.print("비밀번호를 입력해주세요: ");
        String password = sc.nextLine();

        MemberDTO member = new MemberDTO(email, name, password);
        dao.saveMember(member);
        System.out.println("회원가입이 완료되었습니다.");
    }

    public void login() {
    	System.out.println("==============================================");
		System.out.println(" __       _____  _____    __   _    _ ");
		System.out.println("|  |     |  _  ||     |  |  | | |  | |");
		System.out.println("|  |     | | | ||   __|  |  | |  |_| |");
		System.out.println("|  |     | | | ||  | __  |  | |      |");
		System.out.println("|  |     | | | ||  ||  | |  | |  _   |");
		System.out.println("|  |___  | |_| ||  |_| | |  | | | |  |");
		System.out.println("|______| |_____||______| |__| |_|  |_|");
		System.out.println("==============================================");
        System.out.print("이메일 입력: ");
        String email = sc.nextLine();
        System.out.print("비밀번호 입력: ");
        String password = sc.nextLine();

        MemberDTO member = dao.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            currentMember = member;
            System.out.println(member.getName() + "님, 환영합니다!");
        } else {
            System.out.println("이메일 또는 비밀번호가 잘못되었습니다.");
        }
    }

    // ✅ 로그인한 회원의 장바구니에 제품 추가 후 member.txt 갱신
    public void addProductToPocket(String product, long price) {
        if (currentMember == null) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }
        currentMember.getPocket().addItem(product, price);
        dao.saveAllMembers(); // 장바구니 변경 즉시 member.txt 반영
    }

    // ✅ 로그인한 회원의 장바구니 보기
    public void showMyPocket() {
        if (currentMember == null) {
            System.out.println("로그인 후 이용 가능합니다.");
            return;
        }
        currentMember.getPocket().showItems();
    }
}
