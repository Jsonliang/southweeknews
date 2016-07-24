package com.example.administrator.southweek.ui.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class UserInfo implements Parcelable{

    private String name ;
    private String location ;
    private String img ;
    private String gender ;

    private String hobby ;
    private String sign ;

    public UserInfo(){

    }
    protected UserInfo(Parcel in) {
        name = in.readString();
        location = in.readString();
        img = in.readString();
        gender = in.readString();
        hobby = in.readString();
        sign = in.readString();
    }

    public static final Creator<UserInfo> CREATOR =
            new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(img);
        dest.writeString(gender);
        dest.writeString(hobby);
        dest.writeString(sign);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", img='" + img + '\'' +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
