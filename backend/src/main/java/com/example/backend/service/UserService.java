package com.example.backend.service;

import com.example.backend.config.security.PBKDF2Util;
import com.example.backend.domain.system.user.Role;
import com.example.backend.domain.system.user.RoleRepository;
import com.example.backend.domain.system.user.User;
import com.example.backend.domain.system.user.UserRepository;
import com.example.backend.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PBKDF2Util pbkdf2Util;

    @Override
    public User loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepo.findById(userId);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(
                    "Can't find user."
            );
        }
        User user = userOptional.get();

        return user;
    }

    public UserDto.UserRes createUser(UserDto.SignUpReq req) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String passwordEnc = pbkdf2Util.createHash(req.getPassword());

        // DEFAULT ROLE SELECT
        Role defaultRole = roleRepo.findByName("ROLE_USER").orElseThrow(EntityNotFoundException::new);

        User userNew = User.builder()
                //일반 사용자 회원가입 시 입력도 포함. id, pw, pwConfirm, email 은 필수값.
                .userId(req.getUserId())
                .password(passwordEnc)
                .userNm(req.getUserNm())
                .email(req.getEmail())
                .cellNo(req.getCellNo())
                .useYn("Y")
                .admYn("N")
                .joinDt(req.getJoinDt())
                .comment(req.getComment())
                .roles(defaultRole)
                .build();

        User user = userRepo.save(userNew);
        return user.toUserRes();
    }

}