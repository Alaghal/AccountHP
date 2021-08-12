package com.homeproducts.homeacc.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class UserAccount {
    @Id
    private String id;
    private String accountHolderId;
    private Date  createDateAccount;
    private AccountType accountType;
    private double balance;
}
