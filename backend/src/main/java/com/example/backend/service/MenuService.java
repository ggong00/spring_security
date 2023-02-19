package com.example.backend.service;

import com.example.backend.domain.system.menu.Menu;
import com.example.backend.domain.system.menu.MenuRepository;
import com.example.backend.domain.system.menu_role.MenuRole;
import com.example.backend.domain.system.role.Role;
import com.example.backend.domain.system.role.RoleRepository;
import com.example.backend.domain.system.role_privilege.RolePrivilege;
import com.example.backend.domain.system.user.UserRepository;
import com.example.backend.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepo;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public List<MenuDto> getMenuList() {
        List<MenuDto> res = menuRepo.findByParentIsNull()
                .stream().map(Menu::toMenuDto)
                .collect(Collectors.toList());

        return res;
    }

    public List<MenuDto> getMyMenuList(){

        // 1) 로그인 사용자 정보 수신
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2) 사용자 인증 정보에서 권한 확인. 1사용자 1롤.
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        String roleNm = authorities.get(0).getAuthority();

        Role role = roleRepo.findByName(roleNm).orElseThrow(EntityNotFoundException::new);

        // 3) 보유 권한에 해당하는 각 상위 메뉴 식별자 추출, 메뉴 리스트 생성.
        Set<Long> menuIds = new LinkedHashSet<>();

        for(MenuRole mr : role.getMenus()){
            menuIds.add(mr.getMenu().getId());
        }

        List<Menu> allowed = new ArrayList<>();
        List<Menu> menus = menuRepo.findByParentIsNull();
        for(Menu m : menus){
            if(menuIds.contains(m.getId())){
                allowed.add(m);
            }
        }

//      4) res
        return allowed.stream().map(Menu::toMenuDto).collect(Collectors.toList());
    }

}
