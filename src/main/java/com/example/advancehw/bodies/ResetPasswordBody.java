package com.example.advancehw.bodies;

import com.sun.mail.imap.protocol.INTERNALDATE;
import lombok.Data;

@Data
public class ResetPasswordBody {
    public int userId;
    public String newPassword;
}
