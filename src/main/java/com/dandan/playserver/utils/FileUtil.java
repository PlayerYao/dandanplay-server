package com.dandan.playserver.utils;

import cn.hutool.core.text.CharSequenceUtil;
import com.dandan.playserver.global.CustomException;
import com.dandan.playserver.global.ResultEnum;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文件处理帮助类
 *
 * @author JustYao
 */
public class FileUtil {

    private FileUtil() {
    }

    /**
     * 获取路径下所有子文件列表
     *
     * @param path 路径
     * @return {@link File>}
     **/
    public static List<File> getSubFileList(String path){
        File[] fileArr;
        //判断路径是否为空
        if(CharSequenceUtil.isEmpty(path)){
            //路径为空读取根目录
            fileArr = File.listRoots();
        }else{
            //否则读取该路径下所有文件夹和视频
            File file = new File(path);
            if(!file.exists()&&!file.isDirectory()){
                throw new CustomException(ResultEnum.FILE_NOT_FOUND);
            }else {
                //筛选出所有文件夹和MIME Type为video的文件
                fileArr = file.listFiles();
            }
        }
        List<File> files = new ArrayList<>();
        if(fileArr!=null){
            files = Arrays.stream(fileArr).sorted((o1, o2) -> {
                if(o1.isDirectory()==o2.isDirectory()){
                    return o1.getName().compareTo(o2.getName());
                }else if(Boolean.TRUE.equals(o1.isDirectory())){
                    return -1;
                }else {
                    return 1;
                }
            }).collect(Collectors.toList());
        }
        return files;
    }
}
