package com.example.jsonstudy;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zsl
 * @date 2023/1/12
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUser {
    private String id;
    private String userName;
    private String password;

    // @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public SystemUser(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
