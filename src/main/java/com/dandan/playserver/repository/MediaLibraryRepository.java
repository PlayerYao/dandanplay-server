package com.dandan.playserver.repository;

import com.dandan.playserver.entity.DdMediaLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

/**
 * 媒体库repo
 *
 * @author JustYao
 */
@Repository
public interface MediaLibraryRepository extends JpaRepositoryImplementation<DdMediaLibrary,Integer> {
}
