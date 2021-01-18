package com.dandan.playserver.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import oshi.util.FormatUtil;

import java.io.File;

/**
 * 文件返回对象
 *
 * @author JustYao
 */
@Data
@EqualsAndHashCode
public class FileResVo {
    /** 文件名 */
    private String fileName;
    /** 文件路径 */
    private String path;
    /** 是否文件夹 */
    private Boolean isDir;
    /** 文件大小 */
    private String size;


    public FileResVo(File file){
        this.fileName = file.getName();
        this.path = file.getAbsolutePath();
        this.isDir = file.isDirectory();
        this.size= FormatUtil.formatBytes(file.length());
    }
}
