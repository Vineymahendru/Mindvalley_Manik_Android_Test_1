package info.manik.volleyexamples.model;

import android.graphics.Bitmap;

public class ItemObjects {
    private String name;
    private Bitmap photo;

    public ItemObjects(String name, Bitmap photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }
}
