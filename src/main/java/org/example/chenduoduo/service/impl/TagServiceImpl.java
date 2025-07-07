package org.example.chenduoduo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.chenduoduo.model.domain.Tag;
import org.example.chenduoduo.service.TagService;
import org.example.chenduoduo.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author luochen
* @description 针对表【tag】的数据库操作Service实现
* @createDate 2025-05-09 15:23:53
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




