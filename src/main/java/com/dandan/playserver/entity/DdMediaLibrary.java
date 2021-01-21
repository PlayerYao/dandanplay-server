package com.dandan.playserver.entity;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * 媒体库实体类
 *
 * @author JustYao
 */
@Entity
@Table(name = "dd_media_library", schema = "main")
@DynamicUpdate
@NoArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class DdMediaLibrary {
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
    @CreatedDate
    private Date gmtCreate;

    /** 修改时间 */
    @LastModifiedDate
    private Date gmtModified;

    public DdMediaLibrary(String name, String path, Boolean isDir, Boolean isEnable) {
        this.name = name;
        this.path = path;
        this.isDir = isDir;
        this.isEnable = isEnable;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "is_dir")
    public Boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(Boolean isDir) {
        this.isDir = isDir;
    }

    @Basic
    @Column(name = "is_enable")
    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Basic
    @Column(name = "gmt_create")
    @CreatedDate
    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modified")
    @LastModifiedDate
    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DdMediaLibrary that = (DdMediaLibrary) o;
        return isDir == that.isDir && isEnable == that.isEnable && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(path, that.path) && Objects.equals(gmtCreate, that.gmtCreate) && Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, path, isDir, isEnable, gmtCreate, gmtModified);
    }
}
