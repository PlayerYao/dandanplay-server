package com.dandan.playserver.vo;

import cn.hutool.core.text.CharSequenceUtil;
import com.dandan.playserver.global.CustomException;
import com.dandan.playserver.global.DanDanPageRequest;
import com.dandan.playserver.global.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.File;
import java.util.Date;

/**
 * 媒体库vo
 *
 * @author JustYao
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class DdMediaLibraryVo extends DanDanPageRequest {
    /** 主键(自增) */
    private Integer id;

    /** 资源库名称 */
    private String name;

    /** 资源库路径 */
    private String path;

    /** 是否为文件夹 */
    private Boolean isDir;

    /** 是否启用资源库 */
    private Boolean isEnable;

    /** 创建时间 */
    private Date gmtCreate;

    /** 修改时间 */
    private Date gmtModified;

    public void isValidate(){
        if(CharSequenceUtil.isBlank(this.name)||CharSequenceUtil.isBlank(this.path)){
            File file = new File(this.path);
            if(!file.exists()){
                throw new CustomException(ResultEnum.FILE_NOT_FOUND);
            }
            throw new CustomException(ResultEnum.DATA_VALIDATE_FAILD);
        }
    }
}
