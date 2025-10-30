package InOutProject;

public class MemberDTO {
	private String email;
    private String name;
    private String password;

    public MemberDTO(String email, String name, String password) {
    	this.email = email;
    	this.name = name;
        this.password = password;
    }

    // getter
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return email + "," + name + "," + password;
    }

    public static MemberDTO fromString(String line) {
        String[] parts = line.split(",");
        return new MemberDTO(parts[0], parts[1], parts[2]);
    }
}
