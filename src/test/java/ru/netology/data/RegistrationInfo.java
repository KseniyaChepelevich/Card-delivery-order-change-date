package ru.netology.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationInfo {
    private final String address;
    private final String name;
    private final String phoneNumber;
}
