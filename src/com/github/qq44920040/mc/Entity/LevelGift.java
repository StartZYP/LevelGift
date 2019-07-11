package com.github.qq44920040.mc.Entity;

public class LevelGift {
    private String cmd;
    private int MustLevel;
    private String Msg;
    private String DisPass;
    private String ItemName;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getMustLevel() {
        return MustLevel;
    }

    public void setMustLevel(int mustLevel) {
        MustLevel = mustLevel;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getDisPass() {
        return DisPass;
    }

    public void setDisPass(String disPass) {
        DisPass = disPass;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public LevelGift(String cmd, int mustLevel, String msg, String disPass, String itemName) {
        this.cmd = cmd;
        MustLevel = mustLevel;
        Msg = msg;
        DisPass = disPass;
        ItemName = itemName;
    }
}
