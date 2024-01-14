package repository;

import entity.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryImpl implements MenuRepository {
    private List<Menu> menu;

    public MenuRepositoryImpl() {
        this.menu = new ArrayList<>();
        this.menu.add(new Menu(1, "Nasi Goreng", 15000));
        this.menu.add(new Menu(2, "Mie Goreng", 13000));
        this.menu.add(new Menu(3, "Nasi + Ayam", 18000));
        this.menu.add(new Menu(4, "Es Teh Manis", 3000));
        this.menu.add(new Menu(5, "Es Jeruk", 5000));
    }

    @Override
    public List<Menu> getAll() {
        return this.menu;
    }

    @Override
    public Menu getDetailMenuByMenuNumber(int menuNumber) {
        int index = menuNumber - 1;
        return this.menu.get(index);
    }
}
