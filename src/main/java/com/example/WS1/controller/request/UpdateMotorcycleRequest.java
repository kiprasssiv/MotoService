package com.example.WS1.controller.request;

import java.util.UUID;

public class UpdateMotorcycleRequest extends MotorcycleRequest{
    private boolean needFixing;

    public boolean isNeedFixing() {
        return needFixing;
    }

    public void setNeedFixing(boolean needFixing) {
        this.needFixing = needFixing;
    }
}
