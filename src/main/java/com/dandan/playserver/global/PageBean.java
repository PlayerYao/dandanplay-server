package com.dandan.playserver.global;

import cn.hutool.core.util.ArrayUtil;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页返回对象
 *
 * @author JustYao
 */
@Data
public class PageBean<T> {
    /**当前页数.*/
    private int pageNumber;
    /**总记录数.*/
    private int count;
    /**每页记录数.*/
    private int size;
    /**每页的数据的集合*/
    private List<T> list;

//    public PageBean(int page, int count, int totalPage, int size, List<T> list) {
//        this.pageNumber = page;
//        this.count = count;
//        this.totalPage = totalPage;
//        this.size = size;
//        this.list = list;
//    }
//
//    public static PageBean<?> of(List<?> list,int page,int totalPage, int count,int size) {
//        if(ArrayUtil.isEmpty(list)){
//            list=new ArrayList<>();
//        }
//        return new PageBean<>(page,count,totalPage,size,list);
//    }
//
//    public static PageBean<?> of(List<?> list, PageRequest pageRequest){
//        if(ArrayUtil.isEmpty(list)){
//            list=new ArrayList<>();
//        }
//        return new PageBean<>(pageRequest.getPageNumber(),pageRequest.getPageSize())
//    }
}
