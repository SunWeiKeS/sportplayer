package com.vicsun.sportplayer.dao;


import com.vicsun.sportplayer.bean.MainMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao {
    public List<MainMenu>getMenus();
}
