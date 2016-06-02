package cn.solodog.calling;

/**
 * Created by 7 on 2016/6/2.
 */
public class PhoneInfo {
    private String phoneName;
    private String phoneNumber;

    public PhoneInfo(String phoneName,String phoneNumber)
    {
        setPhoneName(phoneName);
        setPhoneNumber(phoneNumber);
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
