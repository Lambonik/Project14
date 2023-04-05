import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    Product prod0 = new Product(0, "a", 10);
    Product prod1 = new Product(1, "b", 20);
    Product prod2 = new Product(2, "c", 30);

    @Test
    public void findByIdIfIdExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        Product expected = prod1;
        Product actual = product.findById(prod1.getId());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findByIdIfIdNotExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        Product actual = product.findById(3);
        Assertions.assertEquals(null, actual);
    }

    @Test
    public void removeIfIdExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        product.remove(prod1.getId());

        Product[] expected = {prod0, prod2};
        Product[] actual = product.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void getExceptionWhenRemoveIfIdNotExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        Assertions.assertThrows(NotFoundException.class, () -> {
            product.remove(-5);
        });
    }

    @Test
    public void addIfIdNotExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        Product prod3 = new Product(3, "d", 40);
        product.add(prod3);

        Product[] expected = {prod0, prod1, prod2, prod3};
        Product[] actual = product.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void getExceptionWhenAddIfIdAlreadyExist() {
        ShopRepository product = new ShopRepository();
        product.add(prod0);
        product.add(prod1);
        product.add(prod2);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            product.add(prod0);
        });
    }
}
