package example.lico.volleyokhttp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by zzk on 16/1/26.
 */
public class GankBean implements Parcelable{
    public boolean error;
    public List<ContentBean> results;


    public static class ContentBean implements Parcelable{
        public String who;
        public String publishedAt;
        public String desc;
        public String type;
        public String url;
        public boolean used;
        public String objectId;
        public String createdAt;
        public String updatedAt;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.who);
            dest.writeString(this.publishedAt);
            dest.writeString(this.desc);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(used ? (byte) 1 : (byte) 0);
            dest.writeString(this.objectId);
            dest.writeString(this.createdAt);
            dest.writeString(this.updatedAt);
        }

        public ContentBean() {
        }

        protected ContentBean(Parcel in) {
            this.who = in.readString();
            this.publishedAt = in.readString();
            this.desc = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.objectId = in.readString();
            this.createdAt = in.readString();
            this.updatedAt = in.readString();
        }

        public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
            public ContentBean createFromParcel(Parcel source) {
                return new ContentBean(source);
            }

            public ContentBean[] newArray(int size) {
                return new ContentBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(error ? (byte) 1 : (byte) 0);
        dest.writeTypedList(results);
    }

    public GankBean() {
    }

    protected GankBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = in.createTypedArrayList(ContentBean.CREATOR);
    }

    public static final Creator<GankBean> CREATOR = new Creator<GankBean>() {
        public GankBean createFromParcel(Parcel source) {
            return new GankBean(source);
        }

        public GankBean[] newArray(int size) {
            return new GankBean[size];
        }
    };
}
