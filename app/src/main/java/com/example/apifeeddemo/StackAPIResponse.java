package com.example.apifeeddemo;

import java.util.List;

class Owner{
    public int reputation;
    public long user_id;
    public String user_type;
    public String profile_image;
    public String display_name;
    public String link;
}
class Item{
    public Owner owner;
    public boolean is_accepted;
    public int score;
    public long last_activity_date;
    public long creation_date;
    public long answer_id;
    public long question_id;
}

public class StackAPIResponse {
    public List<Item> items;
    public boolean hasmore;
    public int backoff;
    public int quota_max;
}
