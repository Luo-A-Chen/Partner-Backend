package org.example.chenduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.chenduoduo.model.domain.Team;
import org.example.chenduoduo.model.domain.User;


/**
* @author luochen
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2025-06-20 16:19:29
*/
public interface TeamService extends IService<Team> {

    long addTeam(Team team, User loginUser);
}
