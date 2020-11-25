package com.vicsun.sportplayer.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//分支导航
@NoArgsConstructor
@Setter
@Getter
public class SubMenu {
    private int id;
    private String title;
    private String path;

    public SubMenu(String title, String path) {
        this.title = title;
        this.path = path;
    }

    @Override
    public String toString() {
        return "SubMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
