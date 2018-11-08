package com.rometools.modules.psc.io;

import com.rometools.modules.psc.types.SimpleChapter;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModule;
import com.rometools.modules.psc.modules.PodloveSimpleChapterModuleImpl;
import com.rometools.rome.feed.module.Module;
import com.rometools.rome.io.ModuleParser;
import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class PodloveSimpleChapterParser implements ModuleParser {

    private static final Namespace NS = Namespace.getNamespace(PodloveSimpleChapterModule.URI);

    @Override
    public String getNamespaceUri() {
        return PodloveSimpleChapterModule.URI;
    }

    @Override
    public Module parse(final Element element, final Locale locale) {
        final Element chaptersElement = element.getChild(PodloveSimpleChapterAttribute.CHAPTERS, NS);
        if (chaptersElement != null) {
            final PodloveSimpleChapterModuleImpl m = new PodloveSimpleChapterModuleImpl();
            final List<Element> es = chaptersElement.getChildren(PodloveSimpleChapterAttribute.CHAPTER, NS);
            if (!es.isEmpty()) {
                final List<SimpleChapter> result = new LinkedList<SimpleChapter>();
                for (Element e : es) {
                    final SimpleChapter c = parseChapter(e);
                    result.add(c);
                }
                m.setChapters(result);
                return m;
            }
        }

        return null;
    }

    private SimpleChapter parseChapter(final Element eChapter) {
        final SimpleChapter chapter = new SimpleChapter();

        final String start = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.START);
        if (start != null) {
            chapter.setStart(start);
        }

        final String title = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.TITLE);
        if (title != null) {
            chapter.setTitle(title);
        }

        final String href = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.HREF);
        if (href != null) {
            chapter.setHref(href);
        }

        final String image = getAttributeValue(eChapter, PodloveSimpleChapterAttribute.IMAGE);
        if (image != null) {
            chapter.setImage(image);
        }

        return chapter;
    }

    protected String getAttributeValue(final Element e, final String attributeName) {
        Attribute attr = e.getAttribute(attributeName);
        if (attr == null) {
            attr = e.getAttribute(attributeName, NS);
        }
        if (attr != null) {
            return attr.getValue();
        } else {
            return null;
        }
    }

}
