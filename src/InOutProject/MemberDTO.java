package InOutProject;

import java.util.List;

public class MemberDTO {
    private String email;
    private String name;
    private String password;
    private Pocket pocket; // 회원별 장바구니

    public MemberDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.pocket = new Pocket(); // 가입 시 새 장바구니
    }

    public MemberDTO(String email, String name, String password, Pocket pocket) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.pocket = pocket; // 로그인 후 복원용
    }

    // getter
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public Pocket getPocket() { return pocket; }

    // member.txt에 저장할 문자열
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(email).append(",").append(name).append(",").append(password);

        if (!pocket.isEmpty()) {
            sb.append(",");
            List<String> items = pocket.getItems();
            List<Long> prices = pocket.getPrices();
            for (int i = 0; i < items.size(); i++) {
                sb.append(items.get(i)).append(":").append(prices.get(i));
                if (i < items.size() - 1) sb.append(";");
            }
        }
        return sb.toString();
    }

    // member.txt에서 읽어오기 (안전 처리 포함)
    public static MemberDTO fromString(String line) {
        if (line == null || line.trim().isEmpty()) return null;

        String[] parts = line.split(",", 4);
        if (parts.length < 3) {
            System.out.println("[경고] 잘못된 회원 데이터 무시: " + line);
            return null;
        }

        MemberDTO member = new MemberDTO(parts[0], parts[1], parts[2]);

        if (parts.length == 4 && parts[3].trim().length() > 0) {
            String[] items = parts[3].split(";");
            for (String item : items) {
                String[] ip = item.split(":");
                if (ip.length == 2) {
                    try {
                        member.getPocket().addItem(ip[0], Long.parseLong(ip[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("[경고] 잘못된 가격 무시: " + item);
                    }
                }
            }
        }

        return member;
    }
}
