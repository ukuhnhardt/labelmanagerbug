package bug.confluence;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.core.ContentEntityObject;
import com.atlassian.confluence.labels.Label;
import com.atlassian.confluence.labels.LabelManager;
import com.atlassian.confluence.labels.Labelable;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;


import java.util.List;
import java.util.Map;

public class PageLabelMacro implements Macro {

    private final LabelManager labelManager;

    public PageLabelMacro(LabelManager labelManager) {
        this.labelManager = labelManager;
    }

    @Override
    public String execute(Map<String, String> stringStringMap, String b, ConversionContext conversionContext) throws MacroExecutionException {
        List<ContentEntityObject> pages = (List<ContentEntityObject>) labelManager.getCurrentContentForLabel(new Label(stringStringMap.get("label")));
        StringBuilder sB = new StringBuilder("<div>");
        for(ContentEntityObject page : pages){
            sB.append(page.getTitle()).append("<br>");
        }
        sB.append("</div>");

        return sB.toString();
    }

    @Override
    public BodyType getBodyType() {
        return BodyType.NONE;
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.BLOCK;
    }
}
