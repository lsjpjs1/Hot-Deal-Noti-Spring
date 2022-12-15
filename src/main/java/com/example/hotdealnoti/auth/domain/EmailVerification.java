package com.example.hotdealnoti.auth.domain;

import com.example.hotdealnoti.auth.security.Role;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.enums.converter.AccountTypeConverter;
import com.example.hotdealnoti.enums.converter.RoleConverter;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table
@AllArgsConstructor
@Builder
@Setter
@DynamicInsert
@ToString
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long emailVerificationId;

    private Long accountId;

    private String emailVerificationCode;

    private String email;

}
