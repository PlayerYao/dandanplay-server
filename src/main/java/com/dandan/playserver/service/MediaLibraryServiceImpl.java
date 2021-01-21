package com.dandan.playserver.service;

import cn.hutool.core.bean.BeanUtil;
import com.dandan.playserver.entity.DdMediaLibrary;
import com.dandan.playserver.repository.MediaLibraryRepository;
import com.dandan.playserver.vo.DdMediaLibraryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 媒体库service实现类
 *
 * @author JustYao
 */
@Service
public class MediaLibraryServiceImpl implements IMediaLibraryService{
    private final MediaLibraryRepository mediaLibraryRepository;

    public MediaLibraryServiceImpl(MediaLibraryRepository mediaLibraryRepository) {
        this.mediaLibraryRepository = mediaLibraryRepository;
    }

    @Override
    public Optional<DdMediaLibrary> getByPrimaryKey(Integer id) {
        return mediaLibraryRepository.findById(id);
    }

    @Override
    public DdMediaLibrary save(DdMediaLibrary mediaLibrary) {
        return mediaLibraryRepository.save(mediaLibrary);
    }

    @Override
    public void deleteById(Integer id) {
        mediaLibraryRepository.deleteById(id);
    }

    @Override
    public List<DdMediaLibrary> findAll() {
        return mediaLibraryRepository.findAll();
    }

    @Override
    public Page<DdMediaLibrary> findPage(DdMediaLibraryVo vo) {
        PageRequest pageRequest = vo.getPageRequest();
        DdMediaLibrary mediaLibrary = new DdMediaLibrary();
        BeanUtil.copyProperties(vo,mediaLibrary);
        Specification<DdMediaLibrary> query = this.getQuery(mediaLibrary);
        return mediaLibraryRepository.findAll(query, pageRequest);
//        return mediaLibraryRepository.findAll(pageRequest);
    }

    /**
     * 获取分页查询的参数
     *
     * @param mediaLibrary 查询对象
     * @return 查询条件
     **/
    private Specification<DdMediaLibrary> getQuery(DdMediaLibrary mediaLibrary){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(mediaLibrary.getId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("id"),mediaLibrary.getId()));
            }
            if(mediaLibrary.getName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+mediaLibrary.getName()+"%"));
            }
            if (mediaLibrary.getPath()!=null){
                predicates.add(criteriaBuilder.like(root.get("path"),"%"+mediaLibrary.getPath()+"%"));
            }
            if(mediaLibrary.getIsDir()!=null){
                predicates.add(criteriaBuilder.equal(root.get("isDir"),mediaLibrary.getIsDir()));
            }
            if(mediaLibrary.getIsEnable()!=null){
                predicates.add(criteriaBuilder.equal(root.get("isEnable"),mediaLibrary.getIsEnable()));
            }
            return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
        };
    }

}
