package com.example.dchikhaoui.myapp.Model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class books  implements Parcelable {
    String isbn;
    String title;
    int price;
    String cover;
    List<String> synopsis;

    public books(String isbn, String title, int price, String cover, List<String> synopsis) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.cover = cover;
        this.synopsis = synopsis;
    }

    protected books(Parcel in) {
        isbn = in.readString();
        title = in.readString();
        price = in.readInt();
        cover = in.readString();
        synopsis = in.createStringArrayList();
    }

    public static final Creator<books> CREATOR = new Creator<books>() {
        @Override
        public books createFromParcel(Parcel in) {
            return new books(in);
        }

        @Override
        public books[] newArray(int size) {
            return new books[size];
        }
    };

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getSynopsis() {
        return synopsis;
    }
    public String getDesc(){
        String mDesc="" ;
        for (int i=0;i<getSynopsis().size();i++){
            mDesc = mDesc + getSynopsis().get(i).toString();
        }
        return mDesc;
    }

    public void setSynopsis(List<String> synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public String toString() {
        return "books{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", price='" + price + '\'' +
                ", cover='" + cover + '\'' +
                ", synopsis=" + synopsis +
                '}';
    }
public String getPriceBook(){
        return String.valueOf(this.price)+"$";
}
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isbn);
        parcel.writeString(title);
        parcel.writeInt(price);
        parcel.writeString(cover);
        parcel.writeStringList(synopsis);
    }
}
