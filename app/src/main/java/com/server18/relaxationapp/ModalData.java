package com.server18.relaxationapp;

import java.io.Serializable;

/**
 * Created by intex on 9/26/2016.
 */
 class ModalData  implements Serializable{

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    private   String video_id;
   private String video_image;
    private String auto_id ;

    public String getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(String auto_id) {
        this.auto_id = auto_id;
    }



    public ModalData(String video_id) {
        this.video_id = video_id;
    }

    public ModalData() {
    }

    public String getVideo_id() {
        return video_id;
    }


}