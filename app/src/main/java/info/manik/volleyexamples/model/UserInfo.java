package info.manik.volleyexamples.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M anik on 03-08-2016.
 */
public class UserInfo {
    private ArrayList<Post> postList = new ArrayList<Post>();

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = (ArrayList<Post>)postList;
    }

    public class Post {
        @SerializedName("mID")
        private String mID;

        @SerializedName("mLikes")
        private String mLikes;



        @SerializedName("width")
        private String mWidth;




        @SerializedName("height")
        private String mHeight;



        @SerializedName("user")
        private UserDetail mUserDetail;

        public String getID() {
            return mID;
        }

        public void setID(String mID) {
            this.mID = mID;
        }


        public String getLikes() {
            return mLikes;
        }

        public void setLikes(String mLikes) {
            this.mLikes = mLikes;
        }

        public String getWidth() {
            return mWidth;
        }

        public void setWidth(String mWidth) {
            this.mWidth = mWidth;
        }
        public String getHeight() {
            return mHeight;
        }

        public void setHeight(String mHeight) {
            this.mHeight = mHeight;
        }

        public UserDetail getUserDetail() {
            return mUserDetail;
        }

        public void setUserDetail(UserDetail mUserDetail) {
            this.mUserDetail = mUserDetail;
        }
      /* getters & setters */
    }
    public class UserDetail{
        @SerializedName("username")
        private String mUserName;

        @SerializedName("profile_image")
        private UserProfileImage mUserProfileImage;

        public String getUserName() {
            return mUserName;
        }

        public void setUserName(String mUserName) {
            this.mUserName = mUserName;
        }

        public UserProfileImage getUserProfileImage() {
            return mUserProfileImage;
        }

        public void setUserProfileImage(UserProfileImage mUserProfileImage) {
            this.mUserProfileImage = mUserProfileImage;
        }


    }
    public class UserProfileImage{
        @SerializedName("small")
        private String mSmallPic;



        @SerializedName("medium")
        private String mMediumPic;


        @SerializedName("large")
        private String mLargePic;


        public String getSmallPic() {
            return mSmallPic;
        }

        public void setSmallPic(String mSmallPic) {
            this.mSmallPic = mSmallPic;
        }


        public String getMediumPic() {
            return mMediumPic;
        }

        public void setMediumPic(String mMediumPic) {
            this.mMediumPic = mMediumPic;
        }


        public String getLargePic() {
            return mLargePic;
        }

        public void setLargePic(String mLargePic) {
            this.mLargePic = mLargePic;
        }

    }
}
