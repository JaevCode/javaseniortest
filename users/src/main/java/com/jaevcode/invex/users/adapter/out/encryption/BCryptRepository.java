package com.jaevcode.invex.users.adapter.out.encryption;

import com.jaevcode.invex.users.application.port.out.EncryptRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class BCryptRepository implements EncryptRepository {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public BCryptRepository() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(String rawText) {
        return bCryptPasswordEncoder.encode(rawText);
    }

    @Override
    public boolean matches(String rawText, String encodedText) {
        return bCryptPasswordEncoder.matches(rawText, encodedText);
    }
}
