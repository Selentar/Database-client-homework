package DBClient.model;

/**
 * Интерфейс общий для всех классов,
 * реализующих взаимодействия класса-модели с базой данных
 */
public interface WrapperInterface {
    void refresh();
    void insert();
    void update();
    void delete();
    void search();
}
