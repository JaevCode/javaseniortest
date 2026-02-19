package com.jaevcode.invex.users.application.port.out;

public interface EncryptRepository {
    public String encode(String rawText);
    public boolean matches(String rawText, String encodedText);
}
