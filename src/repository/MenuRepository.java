package repository;

import entity.Menu;

import java.util.List;

public interface MenuRepository {
    List<Menu> getAll();
    Menu getDetailMenuByMenuNumber(int menuNumber);
}
