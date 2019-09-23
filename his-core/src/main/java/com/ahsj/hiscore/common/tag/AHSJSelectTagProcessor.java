package com.ahsj.hiscore.common.tag;


import com.ahsj.hiscore.core.WebContextUtil;
import com.ahsj.hiscore.entity.sys.Organization;
import com.ahsj.hiscore.entity.sys.SysCodeDetail;
import core.code.Constants;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;
import utils.EmptyUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: his
 * @description:安徽商角开发的thyemelaf扩展
 * @author: chenzhicai
 * @create: 2019-06-27-22-21
 **/
public class AHSJSelectTagProcessor extends AbstractAttributeTagProcessor {
    private static final String ATTR_NAME = "select";
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

    private String departKbn;

    private boolean haveHead;


    public AHSJSelectTagProcessor(final String dialectPrefix) {
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
        html.append("<select ");
        if (!EmptyUtil.Companion.isNullOrEmpty(id)) {
            html.append(" id='").append(id).append("'");
        }
        html.append(" name='").append(name).append("'");
        if (EmptyUtil.Companion.isNullOrEmpty(cssClass)) {
//            html.append(" class='").append("form-control'");
        } else {
            html.append(" class='").append(cssClass).append("'");
        }
        html.append(">");
        if (haveHead) {
            html.append("<option value=''>请选择</option>");
        }
        // option 
        if ("enum".equals(type)) {
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
                    html.append("<option value='").append(sysCodeDetail.getCode()).append("'");
                    if (sysCodeDetail.getCode().equals(value)) {
                        html.append(" selected ");
                    }
                    html.append(">").append(sysCodeDetail.getValue()).append("</option>");
                }
            }
        } else if ("company".equals(type)) {
            // 1:建设单位
            // 2:监理单位
            // 3:施工单位
            // 4:第三方监测单位
            // 5:施工监测单位
            // 6:质检单位
            List<Organization> enumData = (List<Organization>)WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ORANGIATION);
            String parentTreeId = "";
            if (enumData != null && enumData.size() > 0) {
                for (Organization sysOrganization : enumData) {
                    if ("1".equals(sysOrganization.getParentId()) && typeKey.equals(sysOrganization.getType())) {
                        parentTreeId = sysOrganization.getTreeId();
                        continue;
                    }
                    if ("".equals(parentTreeId)) {
                        continue;
                    }
                    if (parentTreeId.equals(sysOrganization.getParentId()) && "1".equals(sysOrganization.getDepartKbn())) {
                        html.append("<option value='").append(sysOrganization.getId()).append("'");
                        if (!EmptyUtil.Companion.isNullOrEmpty(value) && sysOrganization.getId().compareTo(Long.valueOf(value)) == 0) {
                            html.append(" selected ");
                        }
                        html.append(">").append(sysOrganization.getName()).append("</option>");
                    }

                }
            }
        }  else if ("depart".equals(type)) {
            // 1:部门
            // 2:科室
            List<Organization> enumData = (List<Organization>) WebContextUtil.getServletContext().getAttribute(Constants.GLOBAL_DATA_ORANGIATION);
            String parentTreeId = "";
            for (Organization sysOrganization : enumData) {
                if (typeKey.equals(sysOrganization.getType()) && departKbn.equals(sysOrganization.getDepartKbn())) {
                    html.append("<option value='").append(sysOrganization.getId()).append("'");
                    if (!EmptyUtil.Companion.isNullOrEmpty(value) && sysOrganization.getId().compareTo(Long.valueOf(value)) == 0) {
                        html.append(" selected ");
                    }
                    html.append(">").append(sysOrganization.getName()).append("</option>");
                }
            }
        }

        html.append("</select>");



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
        departKbn = tag.getAttributeValue("departKbn");
        haveHead = tag.getAttributeValue("haveHead") == "true" ? true : false;
    }


}

    