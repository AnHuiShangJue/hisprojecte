package com.ahsj.wisdom.common.tag;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import utils.EmptyUtil;

/**
 * @program: his
 * @description:AHSJ单选按钮
 * @author: chenzhicai
 * @create: 2019-06-30-14-53
 **/
public class AHSJRadioTagProcessor extends AbstractAttributeTagProcessor {
    private static final String ATTR_NAME = "radio";
    private static final int PRECEDENCE = 10000;
    /**
     * serialVersionUID
     */

    private static final long serialVersionUID = -8244656461607357792L;
    // id
    private String id = null;
    // 名称
    private String name = null;
    // class
    private String cssClass = null;

    private String value;

    private String type;

    private String typeKey;

    private String title;

    private String cssdefault;


    private boolean haveDefault;


    public AHSJRadioTagProcessor(final String dialectPrefix) {
        super(
                TemplateMode.HTML, // This processor will apply only to HTML mode
                dialectPrefix,     // Prefix to be applied to name for matching
                null,              // No tag name: match any tag name
                false,             // No prefix to be applied to tag name
                ATTR_NAME,         // Name of the attribute that will be matched
                true,              // Apply dialect prefix to attribute name
                PRECEDENCE,        // Precedence (inside dialect's precedence)
                true);             // Remove the matched attribute afterwards
    }


    @Override
    protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName, String attributeValue, IElementTagStructureHandler structureHandler) {
        getData(tag);
        StringBuffer html = new StringBuffer();
        html.append("<h6>" + title + "</h6>");
        int i = 0;
        // radio
/*        if ("enum".equals(type)) {
            ConcurrentHashMap<String, LinkedHashMap<String, Object>> enumData = (ConcurrentHashMap<String, LinkedHashMap<String, Object>>) WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ENUM);
            for (Map.Entry<String, Object> entry : enumData.get(typeKey).entrySet()) {
                html.append("<option value='").append(entry.getKey()).append("'");
                if (entry.getKey().equals(value)) {
                    html.append(" selected ");
                }
                html.append(">").append(entry.getValue()).append("</option>");
            }
        } else if ("code".equals(type)) {
            ConcurrentHashMap<String, List<SysCodeDetail>> enumData = (ConcurrentHashMap<String, List<SysCodeDetail>>) WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_TABLE);
            if (enumData.get(typeKey) != null) {
                for (SysCodeDetail sysCodeDetail : enumData.get(typeKey)) {
                    html.append("<label>");
                    html.append("<input value='").append(sysCodeDetail.getCode()).append("'");
                    html.append("class='").append(cssClass).append("'");
                    html.append("id='").append(id + i).append("'");
                    html.append("name='").append(name).append("'");
                    html.append("type='radio'");
                    html.append("><span>").append(sysCodeDetail.getValue()).append("</span></option>");
                    html.append("</label>");
                    i++;
                }
            }
        }*/
        if (haveDefault) {
            html.append("<label>");
            html.append("<input ");
            html.append("class='").append(cssdefault).append("'");
            html.append("id='").append(id + i).append("'");
            html.append("name='").append(name).append("'");
            html.append("value='").append(value).append("'");
            html.append("type='radio'");
            html.append("checked><span>").append("不选择").append("</span></option>");
            html.append("</label>");
        }

//        final IModelFactory modelFactory = context.getModelFactory();
//
//        final IModel model = modelFactory.createModel();
//        Map<String,String> attributes = new HashMap<>();
//        model.add(modelFactory.createOpenElementTag("div", "class", "input-field col s12 m6"));
//        model.add(modelFactory.createOpenElementTag("div", attributes,);
//        model.add(modelFactory.createText(HtmlEscape.escapeHtml5(html.toString())));
//        model.add(modelFactory.createCloseElementTag("div"));

        structureHandler.replaceWith(
                html, false);
    }

    private void getData(IProcessableElementTag tag) {
        id = tag.getAttributeValue("id");
        name = tag.getAttributeValue("name");
        value = tag.getAttributeValue("value");
        type = tag.getAttributeValue("type");
        typeKey = tag.getAttributeValue("typeKey");
        title = tag.getAttributeValue("title");
        cssClass = EmptyUtil.Companion.isNullOrEmpty(tag.getAttributeValue("cssClass")) ? "" : tag.getAttributeValue("cssClass");
        haveDefault = tag.getAttributeValue("haveDefault").equals("true") ? true : false;
        cssdefault = EmptyUtil.Companion.isNullOrEmpty(tag.getAttributeValue("cssdefault")) ? "cssdefault" : tag.getAttributeValue("cssdefault");

    }


}

    