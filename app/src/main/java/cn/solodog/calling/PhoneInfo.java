package cn.solodog.calling;

/**
 * Created by 7 on 2016/6/2.
 */
public class PhoneInfo {
    private String phoneName;
    private String phoneNumber;
    private String phoneFirst;

    public PhoneInfo(String phoneName,String phoneNumber,String phoneFirst)
    {
        setPhoneName(phoneName);
        setPhoneNumber(phoneNumber);
        setPhoneFirst(phoneFirst);
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

    public void setPhoneFirst(String phoneFirst) {
        this.phoneFirst = phoneFirst;
    }

    public String getPhoneFirst() {
        return phoneFirst;
    }
}
