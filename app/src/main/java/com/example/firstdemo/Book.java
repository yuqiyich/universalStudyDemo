package com.example.firstdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    //develop b add doc
    public int bookId;
    public String bookName="function hitGray(version,phoneNum,phoneNumMd5,deviceId,phoneBrand,androidSkdVersion,channel){\n" +
            "if(version< %s ){\n" +
            "  if(phoneNumMd5 != 'undefined' && phoneNumMd5!= null && phoneNumMd5!= ''\n" +
            "    &&'%s'.indexOf(phoneNumMd5) != -1)\n" +
            "  { \n" +
            "    console.log('phoneNum  has gray list')\n" +
            "    return 1;\n" +
            "  }\n" +
            "  if(/'%s'/.test(phoneNum)&&phoneNum != 'undefined' && phoneNum != null && phoneNum != '')\n" +
            "  { \n" +
            "    console.log('phoneNum rule has hitGray')\n" +
            "    return 1;\n" +
            "  }\n" +
            "}\n" +
            "console.log('no rule match update')\n" +
            "return null\n" +
            "}";
  //develop a add doc
    public Book() {
    }

    public Book(int bookId, String bookName) {
        this.bookId = bookId;
        this.bookName = bookName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookId);
        dest.writeString(this.bookName);
    }

    protected Book(Parcel in) {
        this.bookId = in.readInt();
        this.bookName = in.readString();
    }
    //develop b add doc above CREATOR
    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}