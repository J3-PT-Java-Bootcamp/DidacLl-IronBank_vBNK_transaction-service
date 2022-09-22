package com.ironhack.vbnk_transactionservice.data.http.request;

import com.ironhack.vbnk_transactionservice.utils.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String title;
    private  String message;
    private NotificationType type;
    private  String accountRef;
    private String transactionId;
}
