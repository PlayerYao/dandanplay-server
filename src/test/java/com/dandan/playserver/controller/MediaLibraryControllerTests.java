package com.dandan.playserver.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



/**
 * 媒体库单元测试类
 *
 * @author JustYao
 */
@SpringBootTest
class MediaLibraryControllerTests {

    @Autowired
    private MediaLibraryController mediaLibraryController;

    @Test
    void getFileListTest() {
        Assertions.assertTrue(mediaLibraryController.getFileList(null).size()>0);
        Assertions.assertTrue(mediaLibraryController.getFileList("Z:\\Videos\\动画片\\[Snow-Raws] 私，能力は平均値でって言ったよね！").size()>0);
    }
}
