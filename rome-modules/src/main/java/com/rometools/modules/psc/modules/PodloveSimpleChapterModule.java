package com.rometools.modules.psc.modules;

import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.module.Module;

import java.util.List;

public interface PodloveSimpleChapterModule extends Module, CopyFrom {

    String URI = "http://podlove.org/simple-chapters";

    List<SimpleChapter> getChapters();
    void setChapters(List<SimpleChapter> chapters);
}
