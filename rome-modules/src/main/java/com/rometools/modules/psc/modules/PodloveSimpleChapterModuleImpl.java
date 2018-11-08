package com.rometools.modules.psc.modules;

import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.CopyFrom;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
import com.rometools.rome.feed.module.ModuleImpl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PodloveSimpleChapterModuleImpl
        extends ModuleImpl
        implements PodloveSimpleChapterModule, Cloneable, Serializable {

    private List<SimpleChapter> chapters;

    public PodloveSimpleChapterModuleImpl() {
        super(PodloveSimpleChapterModule.class, PodloveSimpleChapterModule.URI);
    }

    @Override
    public List<SimpleChapter> getChapters() {
        return (chapters==null) ? (chapters= new LinkedList<SimpleChapter>()) : chapters;
    }

    @Override
    public void setChapters(final List<SimpleChapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public Class<? extends CopyFrom> getInterface() {
        return PodloveSimpleChapterModule.class;
    }

    @Override
    public void copyFrom(final CopyFrom obj) {
        final PodloveSimpleChapterModule mod = (PodloveSimpleChapterModule) obj;
        final List<SimpleChapter> chapters = new LinkedList<SimpleChapter>();
        for(SimpleChapter chapter : mod.getChapters()) {
            final SimpleChapter sc = new SimpleChapter();
            sc.copyFrom(chapter);
            chapters.add(sc);
        }
        setChapters(chapters);
    }

    @Override
    public String getUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final PodloveSimpleChapterModuleImpl mod = new PodloveSimpleChapterModuleImpl();
        final List<SimpleChapter> result = new LinkedList<SimpleChapter>();
        for (SimpleChapter chapter : this.chapters){
            SimpleChapter sc = new SimpleChapter();
            sc.copyFrom(chapter);
            result.add(sc);
        }
        result.subList(0, result.size()); // not sure why I need to do this
        mod.setChapters(result);
        return mod;
    }

    @Override
    public boolean equals(final Object obj) {
        final EqualsBean eBean = new EqualsBean(PodloveSimpleChapterModuleImpl.class, this);
        return eBean.beanEquals(obj);
    }

    @Override
    public int hashCode() {
        final EqualsBean equals = new EqualsBean(PodloveSimpleChapterModuleImpl.class, this);
        return equals.beanHashCode();
    }

    @Override
    public String toString() {
        final ToStringBean tsBean = new ToStringBean(PodloveSimpleChapterModuleImpl.class, this);
        return tsBean.toString();
    }
}
