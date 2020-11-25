package com.vicsun.sportplayer.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QueryInfo {
    private String query;//查询信息 username
    private int pageNum = 1;//当前页
    private int pageSize = 1;//每页最大数
}
