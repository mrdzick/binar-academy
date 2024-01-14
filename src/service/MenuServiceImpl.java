package service;

import entity.Menu;
import repository.MenuRepository;

import java.util.List;

public class MenuServiceImpl implements MenuService {
    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenu() {
        return this.menuRepository.getAll();
    }

    @Override
    public Menu getDetailMenu(int menuNumber) {
        return this.menuRepository.getDetailMenuByMenuNumber(menuNumber);
    }
}
