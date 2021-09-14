package model;

import android.os.Parcel;
import android.os.Parcelable;

public class Orang implements Parcelable {

    private String name, address;
    private int age;

    public Orang() {
        this.name = "";
        this.address = "";
        this.age = 0;
    }

    public Orang(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    protected Orang(Parcel in) {
        name = in.readString();
        address = in.readString();
        age = in.readInt();
    }

    public static final Creator<Orang> CREATOR = new Creator<Orang>() {
        @Override
        public Orang createFromParcel(Parcel in) {
            return new Orang(in);
        }

        @Override
        public Orang[] newArray(int size) {
            return new Orang[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(age);
    }
}
