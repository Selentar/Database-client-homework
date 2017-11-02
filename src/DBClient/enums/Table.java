package DBClient.enums;

import DBClient.model.WrapperInterface;
import DBClient.view.overviews.OverviewInterface;

/**
 * Данный класс содержит служебные данные для каждой таблицы
 */
public class Table {
    private final String name;
    private final String overviewPath;
    private final String editViewPath;
    private WrapperInterface wrapper;
    private OverviewInterface overviewController;

    Table(String name, String overviewPath, String editViewPath) {
        this.name = name;
        this.overviewPath = overviewPath;
        this.editViewPath = editViewPath;
    }

    public String getName() {
        return name;
    }

    public String getOverviewPath() {
        return overviewPath;
    }

    public String getEditViewPath() {
        return editViewPath;
    }

    public WrapperInterface getWrapper() { return wrapper; }

    public OverviewInterface getOverviewController() {
        return overviewController;
    }

    public void setWrapper(WrapperInterface wrapperInterface) {
        this.wrapper = wrapperInterface;
    }

    public void setOverviewController(OverviewInterface overviewController) {
        this.overviewController = overviewController;
    }
}
