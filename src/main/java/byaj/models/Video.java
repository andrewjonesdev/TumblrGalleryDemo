package byaj.models;

import byaj.repositories.UserRepository;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Created by student on 6/27/17.
 */
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_id")
    //@NotNull
    //@Min(1)
    private int videoID;

    @ManyToMany(mappedBy = "likes",fetch = FetchType.LAZY)
    private Collection<User> users;

    private String videoName;

    @Lob
    @Type(type = "text")
    private String videoDescription;
    
    private String videoAuthor;

    @Column(columnDefinition="integer default -1")
    private int videoUser;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String picUrl;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String picOriginUrl;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String picDefaultUrl;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String vidUrl;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String vidOriginUrl;

    @Lob
    @Type(type = "text")
    //@Column(columnDefinition="blob default http://res.cloudinary.com/andrewjonesdev/image/upload/c_fill,h_100,w_100/v1499897311/Empty_xay49d.png")
    private String vidDefaultUrl;

    private Date videoDate=new Date();


    public Video(String videoName, String videoDescription, String picUrl, String picOriginUrl, String picDefaultUrl, String vidUrl, String vidOriginUrl, String vidDefaultUrl){

        this.videoName = videoName;
        this.videoDescription = videoDescription;
        this.picUrl = picUrl;
        this.picOriginUrl = picOriginUrl;
        this.picDefaultUrl = picDefaultUrl;
        this.vidUrl = vidUrl;
        this.vidOriginUrl = vidOriginUrl;
        this.vidDefaultUrl = vidDefaultUrl;
    }

    public Video(){

    }

    public int getVideoID() {
        return videoID;
    }

    /*public void setMateID(int videoID) {
        this.videoID = videoID;
    }*/
    public String getVideoName() {
        return videoName;
    }

    public void setVideoName (String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription (String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl (String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicOriginUrl() {
        return picOriginUrl;
    }

    public void setPicOriginUrl (String picOriginUrl) {
        this.picOriginUrl = picOriginUrl;
    }

    public String getPicDefaultUrl() {
        return picDefaultUrl;
    }

    public void setPicDefaultUrl (String picDefaultUrl) {
        this.picDefaultUrl = picDefaultUrl;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl (String vidUrl) {
        this.vidUrl = vidUrl;
    }

    public String getVidOriginUrl() {
        return vidOriginUrl;
    }

    public void setVidOriginUrl (String vidOriginUrl) {
        this.vidOriginUrl = vidOriginUrl;
    }

    public String getVidDefaultUrl() {
        return vidDefaultUrl;
    }

    public void setVidDefaultUrl (String vidDefaultUrl) {
        this.vidDefaultUrl = vidDefaultUrl;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public void setVideoAuthor (String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }

    public int getVideoUser() {
        return videoUser;
    }

    public void setVideoUser (int videoUser) {
        this.videoUser = videoUser;
    }

    public Date getVideoDate() {
        return videoDate;
    }

    public String getFormatDate(){
        SimpleDateFormat format = new SimpleDateFormat("EEEE MMMMM dd, yyyy hh:mm a zzzz", Locale.US);
        return format.format(videoDate);
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public boolean usersContains(User user){

        if (users.contains(user)) {
            return true;
        } else {
            return false;
        }
    }



}
