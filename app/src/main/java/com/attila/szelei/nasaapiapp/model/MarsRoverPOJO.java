package com.attila.szelei.nasaapiapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MarsRoverPOJO
{
    @SerializedName("photos")
    @Expose
    private List<Photo> photos = null;

    public List<Photo> getPhotos() {
        return photos;
    }


    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public class Camera {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("rover_id")
        @Expose
        private int roverId;
        @SerializedName("full_name")
        @Expose
        private String fullName;

        //Constructor
        public Camera(int id, String name, int roverId, String fullName) {
            this.id = id;
            this.name = name;
            this.roverId = roverId;
            this.fullName = fullName;
        }
        //Getters,setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRoverId() {
            return roverId;
        }

        public void setRoverId(int roverId) {
            this.roverId = roverId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

    }
    public  class Photo {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("sol")
        @Expose
        private int sol;
        @SerializedName("camera")
        @Expose
        private Camera camera;
        @SerializedName("img_src")
        @Expose
        private String imgSrc;
        @SerializedName("earth_date")
        @Expose
        private String earthDate;
        @SerializedName("rover")
        @Expose
        private Rover rover;

        public Photo(int id, int sol, Camera camera, String imgSrc, String earthDate, Rover rover) {
            this.id = id;
            this.sol = sol;
            this.camera = camera;
            this.imgSrc = imgSrc;
            this.earthDate = earthDate;
            this.rover = rover;
        }
        public String addCharacter(String string, char ch, int position) {
            StringBuilder sb = new StringBuilder(string);
            sb.insert(position, ch);
            return sb.toString();
        }

        public Photo(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSol() {
            return sol;
        }

        public void setSol(int sol) {
            this.sol = sol;
        }

        public Camera getCamera() {
            return camera;
        }

        public void setCamera(Camera camera) {
            this.camera = camera;
        }

        public String getImgSrc() {
            return addCharacter(imgSrc,'s',4);
        }

        public void setImgSrc(String imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getEarthDate() {
            return earthDate;
        }

        public void setEarthDate(String earthDate) {
            this.earthDate = earthDate;
        }

        public Rover getRover() {
            return rover;
        }

        public void setRover(Rover rover) {
            this.rover = rover;
        }

    }
    public class Rover {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("landing_date")
        @Expose
        private String landingDate;
        @SerializedName("launch_date")
        @Expose
        private String launchDate;
        @SerializedName("status")
        @Expose
        private String status;

        public Rover(int id, String name, String landingDate, String launchDate, String status) {
            this.id = id;
            this.name = name;
            this.landingDate = landingDate;
            this.launchDate = launchDate;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLandingDate() {
            return landingDate;
        }

        public void setLandingDate(String landingDate) {
            this.landingDate = landingDate;
        }

        public String getLaunchDate() {
            return launchDate;
        }

        public void setLaunchDate(String launchDate) {
            this.launchDate = launchDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

    }

}
