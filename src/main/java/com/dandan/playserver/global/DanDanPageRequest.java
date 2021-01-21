package com.dandan.playserver.global;

import cn.hutool.core.text.CharSequenceUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 请求分页对象
 *
 * @author JustYao
 */
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class DanDanPageRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private int pageNumber=0;
    private int pageSize=10;
    private String sort="desc";
    private String orderBy="gmtModified";

    public DanDanPageRequest(int page, int size, String sort,String orderBy) {
        Assert.notNull(sort, "Sort must not be null!");
        this.sort=sort;
        this.pageNumber = page;
        this.pageSize = size;
        this.orderBy=orderBy;
    }

    /**
     * 获取jpa分页器
     *
     * @return {@link PageRequest}
     **/
    public PageRequest getPageRequest() {
        if(CharSequenceUtil.isBlank(this.sort)||CharSequenceUtil.isBlank(this.orderBy)){
            return PageRequest.of(pageNumber,pageSize);
        }else{
            Sort orders = Sort.by(Sort.Direction.fromString(this.sort),this.orderBy);
            return PageRequest.of(pageNumber, pageSize, orders);
        }
    }

}
