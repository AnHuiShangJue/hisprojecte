package com.ahsj.hiscore.common.tag.dialect;

import com.ahsj.hiscore.common.tag.AHSJRadioTagProcessor;
import com.ahsj.hiscore.common.tag.AHSJSelectTagProcessor;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: his
 * @description:AHSJSelectDialect
 * @author: chenzhicai
 * @create: 2019-06-27-22-34
 **/
public class AHSJDialect extends AbstractProcessorDialect {

    public AHSJDialect() {
        super(
                "AHSJ",    // Dialect name
                "ahsj",            // Dialect prefix (hello:*)
                1000);              // Dialect precedence
    }

    /*
     * Initialize the dialect's processors.
     *
     * Note the dialect prefix is passed here because, although we set
     * "hello" to be the dialect's prefix at the constructor, that only
     * works as a default, and at engine configuration time the user
     * might have chosen a different prefix to be used.
     */
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        processors.add(new AHSJSelectTagProcessor(dialectPrefix));
        processors.add(new AHSJRadioTagProcessor(dialectPrefix));
        return processors;
    }
}

    