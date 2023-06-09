package com.example.projectdeploy.SimilarPosts;

public class JsonParsing {
    Description DescriptionObject;
    Postid PostidObject;
    _meta _metaObject;
    Id IdObject;


    // Getter Methods

    public Description getDescription() {
        return DescriptionObject;
    }

    public Postid getPostid() {
        return PostidObject;
    }

    public _meta get_meta() {
        return _metaObject;
    }

    public Id getId() {
        return IdObject;
    }

    // Setter Methods

    public void setDescription(Description descriptionObject) {
        this.DescriptionObject = descriptionObject;
    }

    public void setPostid(Postid postidObject) {
        this.PostidObject = postidObject;
    }

    public void set_meta(_meta _metaObject) {
        this._metaObject = _metaObject;
    }

    public void setId(Id idObject) {
        this.IdObject = idObject;
    }
}
class Id {
    private String raw;


    // Getter Methods

    public String getRaw() {
        return raw;
    }

    // Setter Methods

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
class _meta {
    private String engine;
    private float score;
    private String id;


    // Getter Methods

    public String getEngine() {
        return engine;
    }

    public float getScore() {
        return score;
    }

    public String getId() {
        return id;
    }

    // Setter Methods

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setId(String id) {
        this.id = id;
    }
}
class Postid {
    private String raw;


    // Getter Methods

    public String getRaw() {
        return raw;
    }

    // Setter Methods

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
class Description {
    private String raw;


    // Getter Methods

    public String getRaw() {
        return raw;
    }

    // Setter Methods

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
