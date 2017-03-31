package net.asg.games.dante.states;


import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

import net.asg.games.dante.models.MovingGameObjectType;


public class MovingGameObjectState implements Serializable {
    private MovingGameObjectType type;
    private int index;
    private int posX = -1;
    private int posY = -1;
    private int hitPosX = -1;
    private int hitPosY = -1;
    private boolean isCollided;
    private boolean isSoundTriggered;
    private boolean isHitboxActive;

    public MovingGameObjectType getType() {
        return type;
    }

    public void setType(MovingGameObjectType type) {
        this.type = type;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setHitPosX(int posX) {
        this.hitPosX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setHitPosY(int posY) {
        this.hitPosY = posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void write(Json json) {
        json.writeValue("posX", posX);
        json.writeValue("posY", posY);
        json.writeValue("type", type.getValue());
        json.writeValue("index", index);
        json.writeValue("isCollided", isCollided);
        json.writeValue("isHitboxActive", isHitboxActive);
        json.writeValue("isSoundTriggered", isSoundTriggered);
    }

    public void read(Json json, JsonValue jsonData) {
        posX = json.readValue("posX", Integer.class, jsonData);
        posY = json.readValue("posY", Integer.class, jsonData);
        index = json.readValue("index", Integer.class, jsonData);
        int val = json.readValue("type", Integer.class, jsonData);
        type = MovingGameObjectType.fromValue(val);
        isCollided = json.readValue("isCollided", Boolean.class, jsonData);
        isHitboxActive = json.readValue("isHitboxActive", Boolean.class, jsonData);
        isSoundTriggered = json.readValue("isSoundTriggered", Boolean.class, jsonData);
    }

    public void setCollided(boolean isCollided) {
        this.isCollided = isCollided;
    }

    public void setHitboxActive(boolean isHitboxActive) {
        this.isHitboxActive = isHitboxActive;
    }

    public void setSoundTriggered(boolean isSoundTriggered) {
        this.isSoundTriggered = isSoundTriggered;
    }

}
