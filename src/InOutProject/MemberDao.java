package InOutProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    private final String filePath = "D:\\ACON_20250916\\01_JAVA\\workspace\\mini_final\\data\\member.txt";
    private List<MemberDTO> members = new ArrayList<>();

    public MemberDao() {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs(); // 폴더 자동 생성
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            System.out.println("회원 파일 생성 실패: " + e.getMessage());
        }
        loadAllMembers(); // 기존 회원 불러오기
    }

    // 회원 등록
    public void saveMember(MemberDTO member) {
        members.add(member);
        saveAllMembers();
    }

    // 이메일로 회원 조회
    public MemberDTO findByEmail(String email) {
        for (MemberDTO m : members) {
            if (m.getEmail().equals(email)) return m;
        }
        return null;
    }

    // 이메일로 회원 삭제
    public boolean deleteMember(String email) {
        boolean deleted = false;
        List<MemberDTO> newList = new ArrayList<>();
        for (MemberDTO m : members) {
            if (m.getEmail().equals(email)) {
                deleted = true;
                continue;
            }
            newList.add(m);
        }
        members = newList;
        if (deleted) saveAllMembers();
        return deleted;
    }

    // 전체 회원 + Pocket 저장
    public void saveAllMembers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (MemberDTO m : members) {
                bw.write(m.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("회원 전체 저장 중 오류: " + e.getMessage());
        }
    }

    // 파일에서 전체 회원 로드
    public void loadAllMembers() {
        members.clear();
        File file = new File(filePath);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                MemberDTO m = MemberDTO.fromString(line);
                if (m != null) members.add(m);
            }
        } catch (IOException e) {
            System.out.println("회원 전체 불러오기 오류: " + e.getMessage());
        }
    }

    public List<MemberDTO> getMembers() {
        return members;
    }
}
