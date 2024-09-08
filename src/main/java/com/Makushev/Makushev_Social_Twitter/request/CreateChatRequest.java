package com.Makushev.Makushev_Social_Twitter.request;

import com.Makushev.Makushev_Social_Twitter.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {

    private Long userId;

}

