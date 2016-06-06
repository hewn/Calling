package cn.solodog.calling;

/**
 * Created by 7 on 2016/6/2.
 */
public class PhoneInfo {
    private String phoneName;
    private String phoneNumber;
    private String phoneFirstl;
    private String phoneFirstn;


    public PhoneInfo(String phoneName,String phoneNumber,String phoneFirstl,String phoneFirstn)
    {
        setPhoneName(phoneName);
        setPhoneNumber(phoneNumber);
        setPhoneFirstl(phoneFirstl);
        setPhoneFirstn(phoneFirstn);

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

    public void setPhoneFirstl(String phoneFirstl) {
        this.phoneFirstl = phoneFirstl;
    }

    public String getphoneFirstl() {
        return phoneFirstl;
    }

    public void setPhoneFirstn(String phoneFirstn) {
        this.phoneFirstn = phoneFirstn;
    }

    public String getPhoneFirstn() {
        return phoneFirstn;
    }


}
