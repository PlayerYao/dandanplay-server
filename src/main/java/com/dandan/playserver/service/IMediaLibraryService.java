package com.dandan.playserver.service;

import com.dandan.playserver.entity.DdMediaLibrary;
import com.dandan.playserver.vo.DdMediaLibraryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * 媒体库service接口
 *
 * @author JustYao
 */
public interface IMediaLibraryService {

    /**
     * 根据主键查询记录
     *
     * @param id 主键
     * @return {@link DdMediaLibrary>}
     **/
    Optional<DdMediaLibrary> getByPrimaryKey(Integer id);

    /**
     * 更新或插入对象
     *
     * @param mediaLibrary 需要保存的对象
     * @return {@link DdMediaLibrary}
     **/
    DdMediaLibrary save(DdMediaLibrary mediaLibrary);

    /**
     * 主键删除
     *
     * @param id 主键
     **/
    void deleteById(Integer id);

    /**
     * 查询全部
     *
     * @return {@link List<DdMediaLibrary>}
     **/
    List<DdMediaLibrary> findAll();

    /**
     * 分页查询
     *
     * @param vo 带分页的vo对象
     * @return {@link Page<DdMediaLibrary>}
     **/
    Page<DdMediaLibrary> findPage(DdMediaLibraryVo vo);


}
