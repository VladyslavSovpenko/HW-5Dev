package org.example.model.pet;

public class Pet {
    private long id;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    private String name;
    private Tags tags;
    private String status;

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }
//    private PhotoUrls photoUrls;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public PhotoUrls getPhotoUrls() {
//        return photoUrls;
//    }
//
//    public void setPhotoUrls(PhotoUrls photoUrls) {
//        this.photoUrls = photoUrls;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
