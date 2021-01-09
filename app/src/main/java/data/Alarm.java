package data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Alarm {
    @PrimaryKey
    public int id;
    public String name;
    public float xCoord;
    public float yCoord;
}
