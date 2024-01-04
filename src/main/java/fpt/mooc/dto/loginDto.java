package fpt.mooc.dto;

import javax.validation.constraints.NotBlank;

public class loginDto {
    @NotBlank
    private String userName;
    @NotBlank
    private String passWord;

    public loginDto(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
