package com.dandan.playserver.controller;

import cn.hutool.core.bean.BeanUtil;
import com.dandan.playserver.entity.DdMediaLibrary;
import com.dandan.playserver.global.ApiResult;
import com.dandan.playserver.global.CustomException;
import com.dandan.playserver.global.ResultEnum;
import com.dandan.playserver.service.IMediaLibraryService;
import com.dandan.playserver.utils.FileUtil;
import com.dandan.playserver.vo.DdMediaLibraryVo;
import com.dandan.playserver.vo.FileResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 媒体库Controller
 *
 * @author JustYao
 */
@RestController
@RequestMapping("/media/library")
public class MediaLibraryController {
    private final IMediaLibraryService mediaLibraryService;

    public MediaLibraryController(IMediaLibraryService mediaLibraryService) {
        this.mediaLibraryService = mediaLibraryService;
    }

    /**
     * 根据路径查询下属文件
     *
     * @param pre 路径
     * @return {@link List<FileResVo>} 文件列表
     **/
    @PostMapping("/file/list")
    public List<FileResVo> getFileList(@RequestBody(required = false) String pre){
        //准备返回数据
        List<FileResVo> files = new ArrayList<>();
        //如果子文件列表不为空就筛选出视频和文件夹并文件对象进行转换为所需要的vo对象
        List<File> subFileList = FileUtil.getSubFileList(pre);
        if(subFileList!=null){
            files = subFileList.stream().filter(subFile -> {
                String mimeType;
                try {
                    mimeType = Files.probeContentType(subFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new CustomException(e.getMessage());
                }
                return subFile.isDirectory() || (mimeType != null && mimeType.startsWith("video"));
            }).map(FileResVo::new).collect(Collectors.toList());
        }
        return files;
    }

    /**
     * 添加一个媒体库记录
     *
     * @param vo 包含name和path的vo
     * @return com.dandan.playserver.entity.DdMediaLibrary
     **/
    @PostMapping
    public DdMediaLibrary add(DdMediaLibraryVo vo){
        vo.isValidate();
        boolean isDir = new File(vo.getPath()).isDirectory();
        DdMediaLibrary library = new DdMediaLibrary(vo.getName(), vo.getPath(), isDir, true);
        return mediaLibraryService.save(library);
    }

    /**
     * 修改一个媒体库记录
     *
     * @param vo 待修改的vo
     * @return com.dandan.playserver.entity.DdMediaLibrary
     **/
    @PutMapping
    public DdMediaLibrary update(DdMediaLibraryVo vo){
        vo.isValidate();
        DdMediaLibrary library = new DdMediaLibrary();
        BeanUtil.copyProperties(vo,library);
        return mediaLibraryService.save(library);
    }
    /**
     * 切换启用禁用开关
     *
     * @param id 媒体库id
     * @return com.dandan.playserver.entity.DdMediaLibrary
     **/
    @PutMapping("/switch")
    public DdMediaLibrary switchState(int id){
        Optional<DdMediaLibrary> optional = mediaLibraryService.getByPrimaryKey(id);
        return optional.map(mediaLibrary -> {
            mediaLibrary.setIsEnable(!mediaLibrary.getIsEnable());
            return mediaLibraryService.save(mediaLibrary);
        }).orElseThrow(() -> {
            throw new CustomException(ResultEnum.DATA_VALIDATE_FAILD);
        });
    }

    /**
     * 删除一个媒体库记录
     *
     * @param id 被删除的id
     * @return com.dandan.playserver.global.ApiResult
     **/
    @DeleteMapping
    public ApiResult delete(int id){
        mediaLibraryService.deleteById(id);
        return ApiResult.success("删除成功");
    }
}
