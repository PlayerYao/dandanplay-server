package com.dandan.playserver.service;

import cn.hutool.core.lang.Assert;
import com.dandan.playserver.entity.DdMediaLibrary;
import com.dandan.playserver.vo.DdMediaLibraryVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 媒体库service测试类
 *
 * @author JustYao
 */
@SpringBootTest
class MediaLibraryServiceTests {
    @Autowired
    private IMediaLibraryService mediaLibraryService;

    @Test
    void save(){
        //测试插入
        DdMediaLibrary library = new DdMediaLibrary("测试", "D:\\", true, true);
        library.setGmtCreate(new Date());
        DdMediaLibrary save = mediaLibraryService.save(library);
        assertNotNull(save.getId());
        //测试修改
        save.setName("已修改");
        save.setIsEnable(false);
        DdMediaLibrary edit = mediaLibraryService.save(save);
        assertNotNull(edit.getGmtModified());
    }


    @Test
    void getByPrimaryKey(){
        Optional<DdMediaLibrary> optional = mediaLibraryService.getByPrimaryKey(4);
        assertNotNull(optional.get());
    }
    @Test
    void deleteById(){
        mediaLibraryService.deleteById(5);
    }

    @Test
    void findPage(){
        DdMediaLibraryVo vo = new DdMediaLibraryVo();
        vo.setName("测");
        vo.setIsEnable(true);
        vo.setPageNumber(0);
        vo.setPageSize(10);
        Page<DdMediaLibrary> page = mediaLibraryService.findPage(vo);
        assertNotNull(page);
        System.out.println("pageNumber:"+page.getNumber()+"pageSize:"+page.getSize()+"totalPages:"+page.getTotalPages());
        List<DdMediaLibrary> content = page.getContent();
        System.out.println(content);
    }


    @Test
    void findAll(){
        List<DdMediaLibrary> all = mediaLibraryService.findAll();
        all.forEach(System.out::println);
        assertNotNull(all);
    }
}
