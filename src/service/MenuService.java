package service;

import entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getAllMenu();
    Menu getDetailMenu(int menuNumber);
}
