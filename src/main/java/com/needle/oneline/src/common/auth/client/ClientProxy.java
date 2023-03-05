package com.needle.oneline.src.common.auth.client;

import com.needle.oneline.src.user.User;

public interface ClientProxy {
    User getUserData(String accessToken);
}
