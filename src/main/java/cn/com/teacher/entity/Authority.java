package cn.com.teacher.entity;
import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {


    private Long id;

    private String authority;

    public Authority(String authority) {
        super();
        this.authority = authority;
    }

    public Authority(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}