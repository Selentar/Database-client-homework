package DBClient.enums;

/**
 * Перечисление всех доступных пользователю таблиц
 */
public enum Tables {
    Students;

    Table students = new Table("Students",
            "/DBClient/view/overviews/StudentsOverview.fxml",
            "/DBClient/view/edit/StudentsEditView.fxml");

    /**
     * Возвращает класс со всеми служебными данными для таблицы
     * @return
     */
    public Table getTable() {
        return students;
    }
}
