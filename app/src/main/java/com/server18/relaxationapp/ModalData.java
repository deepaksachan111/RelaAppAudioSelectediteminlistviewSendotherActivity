package com.server18.relaxationapp;

import java.io.Serializable;

/**
 * Created by intex on 9/26/2016.
 */
 class ModalData  implements Serializable{

    String video_id;
    String video_image;

    public ModalData(String video_id) {
        this.video_id = video_id;
    }

    public ModalData() {
    }

    public String getVideo_id() {
        return video_id;
    }


}