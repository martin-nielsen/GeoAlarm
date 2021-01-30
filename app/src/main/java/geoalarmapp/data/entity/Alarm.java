package geoalarmapp.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "alarm_table")
public class Alarm {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    public String name;
    public Double latitude;
    public Double longitude;
}
