package com.dandan.playserver.controller;

import com.dandan.playserver.global.CustomException;
import com.dandan.playserver.utils.FileUtil;
import com.dandan.playserver.vo.FileResVo;
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

}
