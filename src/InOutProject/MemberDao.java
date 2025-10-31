package InOutProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

	private final String filePath = "D:\\ACON_20250916\\01_JAVA\\workspace\\final_1\\data\\member.txt";

    public MemberDao() {
        File file = new File(filePath);
        try {
            file.getParentFile().mkdirs(); // 폴더 자동 생성
            if (!file.exists()) {
                file.createNewFile();      // 파일 없으면 생성
            }
        } catch (IOException e) {
            System.out.println("회원 파일 생성 실패: " + e.getMessage());
        }
    }

    // 1. 회원 등록
    public void saveMember(MemberDTO member) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(member.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("회원 저장 중 오류 발생: " + e.getMessage());
        }
    }

    // 2. 이메일로 회원 한 명 조회
    public MemberDTO findByEmail(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(email)) {
                    return new MemberDTO(data[0], data[1], data[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("회원 조회 중 오류 발생: " + e.getMessage());
        }
        return null;
    }

    // 3. 이메일로 회원 삭제
    public boolean deleteMember(String email) {
        File inputFile = new File(filePath);
        File tempFile = new File("data/temp_member.txt");

        boolean deleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(email)) {
                    deleted = true; // 삭제할 회원 발견
                    continue;       // 해당 줄은 기록하지 않음
                }
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("회원 삭제 중 오류 발생: " + e.getMessage());
            return false;
        }

        // 기존 파일 삭제 후 임시 파일 이름 변경
        if (deleted) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } else {
            tempFile.delete(); // 삭제된 회원 없으면 임시 파일 삭제
        }

        return deleted;
    }
}
