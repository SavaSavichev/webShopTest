package org.demoTest.model.base;

public class BaseHeaderComponent<Page extends BasePage<?>> extends BaseModel {

    private final Page page;

    public BaseHeaderComponent(Page page) {
        super(page.getDriver());
        this.page = page;
    }

    protected Page getPage() {
        return page;
    }

}
