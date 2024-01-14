import repository.MenuRepository;
import repository.MenuRepositoryImpl;
import repository.OrderRepository;
import repository.OrderRepositoryImpl;
import service.MenuService;
import service.MenuServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;
import view.MenuView;

public class App {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepositoryImpl();
        MenuRepository menuRepository = new MenuRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository, menuRepository);
        MenuService menuService = new MenuServiceImpl(menuRepository);

        MenuView menuView = new MenuView(menuService, orderService);

        menuView.showMenu();
    }
}
