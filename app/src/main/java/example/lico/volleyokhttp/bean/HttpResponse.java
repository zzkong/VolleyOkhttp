package example.lico.volleyokhttp.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zzk on 16/1/26.
 */
public class HttpResponse implements Parcelable{

    public int code;
    public String message;


    public HttpResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.message);
    }

    public HttpResponse() {
    }

    protected HttpResponse(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
    }

    public static final Creator<HttpResponse> CREATOR = new Creator<HttpResponse>() {
        public HttpResponse createFromParcel(Parcel source) {
            return new HttpResponse(source);
        }

        public HttpResponse[] newArray(int size) {
            return new HttpResponse[size];
        }
    };
}
