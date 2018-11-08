package com.rometools.modules.psc.io;

public interface PodloveSimpleChapterAttribute {
    // namespace prefix
    String PREFIX = "psc";

    // name of the list of chapters
    String CHAPTERS = "chapters";
    String VERSION = "version";

    // name of a chapter entry
    String CHAPTER = "chapter";

    // attributes of single chapter entries
    String START = "start";
    String TITLE = "title";
    String HREF = "href";
    String IMAGE = "image";
}
