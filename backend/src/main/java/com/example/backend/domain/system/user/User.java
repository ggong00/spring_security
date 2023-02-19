package com.example.backend.domain.system.user;

import com.example.backend.domain.CommonEntity;
import com.example.backend.domain.system.role.Role;
import com.example.backend.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter // do not create/use `setter` on Entity class
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Entity
@Table(name = "DIM_USER")
public class User extends CommonEntity implements UserDetails {
    @Id
    @Column(length = 20)
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = true, length = 20)
    private String userNm;

    @Column(nullable = true, length = 20)
    private String deptCd;

    @Column(nullable = true, length = 20)
    private String rankCd;

    @Column(nullable = true, length = 20)
    private String cellNo;

    @Column(nullable = true, length = 50)
    private String email;

    @Column(nullable = true)
    private Integer authCd;

    @Column(nullable = true, length = 1)
    private String useYn;

    @Column(nullable = true, length = 1)
    private String admYn;

    @Column(nullable = true, length = 10, columnDefinition="char")
    private String joinDt;

    @Column(nullable = true, length = -1, columnDefinition="text")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role roles;

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateUseYn(String useYn){
        this.useYn = useYn;
    }

    public void updateDetail(UserDto.UpdateUserReq req){
        this.userNm = req.getUserNm();
        this.email = req.getEmail();
        this.cellNo = req.getCellNo();
        this.useYn = req.getUseYn();
        this.admYn = req.getAdmYn();
        this.comment = req.getComment();
    }

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(roles.getName()));
        roles.getPrivileges()
                .stream()
                .map(rp -> new SimpleGrantedAuthority(rp.getPrivilege().getName()))
                .forEach(authorities::add);

        return authorities;
    }

    @Override
    public String getUsername() {
        // TODO: 수정 필요
        return this.userId;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO: 수정 필요
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO: 수정 필요
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO: 수정 필요
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO: 수정 필요
        if(useYn.equals("N") || useYn == null){
            System.out.println("This user has lost access. Contact admin.");
            return false;
        }
        return true;
    }

    public UserDto.UserRes toUserRes(){
        return UserDto.UserRes.builder()
                .userId(this.userId)
                .userNm(this.userNm)
                .cellNo(this.cellNo)
                .email(this.email)
                .joinDt(this.joinDt)
                .comment(this.comment)
                .admYn(this.admYn)
                .useYn(this.useYn)
                .build();
    }
}
