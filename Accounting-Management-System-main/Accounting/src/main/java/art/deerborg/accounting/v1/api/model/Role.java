package art.deerborg.accounting.v1.api.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,ADMIN;
    @Override
    public String getAuthority() {
        return  name();
    }
}
