package com.rometools.modules.psc.io;

import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleGenerator;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PodloveSimpleChapterGenerator implements ModuleGenerator {

    private static final Namespace NS = Namespace.getNamespace(PodloveSimpleChapterAttribute.PREFIX, PodloveSimpleChapterModule.URI);
    private static final Set<Namespace> NAMESPACES;

    static {
        final Set<Namespace> nss = new HashSet<Namespace>();
        nss.add(NS);
        NAMESPACES = Collections.unmodifiableSet(nss);
    }

    @Override
    public final String getNamespaceUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public final Set<Namespace> getNamespaces() {
        return NAMESPACES;
    }

    @Override
    public void generate(Module module, Element element) {
        if (module instanceof PodloveSimpleChapterModule) {
            final PodloveSimpleChapterModule m = (PodloveSimpleChapterModule) module;
            generateChapters(m.getChapters(), element);
        }
    }

    private void generateChapters(List<SimpleChapter> chapters, Element element) {
        final Element cs = new Element(PodloveSimpleChapterAttribute.CHAPTERS, NS);
        for (SimpleChapter c : chapters) {
            cs.addContent(generateChapter(c));
        }
        element.addContent(cs);
    }

    private Element generateChapter(SimpleChapter c) {
        final Element e = new Element(PodloveSimpleChapterAttribute.CHAPTER, NS);

        addNotNullAttribute(e, PodloveSimpleChapterAttribute.START, c.getStart());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.TITLE, c.getTitle());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.HREF, c.getHref());
        addNotNullAttribute(e, PodloveSimpleChapterAttribute.IMAGE, c.getImage());

        return e;
    }

    private void addNotNullAttribute(final Element target, final String name, final Object value) {
        if (target == null || value == null) {
            return;
        } else {
            target.setAttribute(name, value.toString());
        }
    }
}
