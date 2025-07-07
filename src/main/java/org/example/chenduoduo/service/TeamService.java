package org.example.chenduoduo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.chenduoduo.model.domain.Team;
import org.example.chenduoduo.model.domain.User;
import org.example.chenduoduo.model.dto.TeamQuery;
import org.example.chenduoduo.model.request.TeamJoinRequest;
import org.example.chenduoduo.model.request.TeamQuitRequest;
import org.example.chenduoduo.model.request.TeamUpdateRequest;
import org.example.chenduoduo.model.vo.TeamUserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @author luochen
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2025-06-20 16:19:29
*/
public interface TeamService extends IService<Team> {

    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    long addTeam(Team team, User loginUser);

    boolean updateTeam(TeamUpdateRequest team, User loginUser);

    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    @Transactional(rollbackFor = Exception.class)
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

}
