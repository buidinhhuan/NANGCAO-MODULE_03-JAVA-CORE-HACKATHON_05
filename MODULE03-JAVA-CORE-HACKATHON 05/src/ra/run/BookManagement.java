package ra.run;

import ra.config.InputMethods;
import ra.model.CartItem;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CartService;
import ra.service.CatalogService;
import ra.service.ProductService;

import java.util.Collection;
import java.util.List;

public class BookManagement {
    static CatalogService catalogService = new CatalogService();
    static ProductService productService = new ProductService();
    static CartService cartService = new CartService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("" +
                    " **************************BASIC-MENU**************************\n" +
                    "1. Quản lý danh mục\n" +
                    "2. Quản lý sản phẩm\n" +
                    "3. Dành cho người dùng (***)\n" +
                    "4. Thoát");
            System.out.println("Nhập lưa chọ của bạn ");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    //menu quản lí danh muc
                    menuCatalogManager();
                    break;
                case 2:
                    //menu quản lí san pham
                    if (catalogService.getSize() == 0) {
                        System.err.println("Danh sách danh mục trống ");
                    } else {
                        menuProductManager();
                    }
                    break;

                case 3:
                    //  Dành cho người dùng
                    menuUserManager();
                    break;
                case 4:
                    // thoát
                    System.exit(0);
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 3");
            }
        }
    }

    //hàm thêm danh muc
    public static void menuCatalogManager() {
        byte choice = 0;
        while (choice != 5) {
            System.out.println(" ********************CATALOG-MANAGEMENT********************\n" +
                    "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục [5 điểm]\n" +
                    "2. Hiển thị thông tin tất cả các danh mục [5 điểm]\n" +
                    "3. Sửa tên danh mục theo mã danh mục [5 điểm]\n" +
                    "4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm) [5 điểm]\n" +
                    "5. Quay lại\n");
            System.out.println("Nhập vào  lựa chọn của bạn");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // thêm mới danh muc
                    createCatalog();
                    break;
                case 2:
                    //Hiển thị thông tin tất cả các danh mục
                    showListCatalog();
                    break;
                case 3:
                    // Sửa tên danh mục theo mã danh mục
                    editCatalog();
                    break;
                case 4:
                    // Xóa danh muc theo mã danh mục
                    deleteCatalog();
                    break;
                case 5:
                    // quay lại
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 4");
                    break;
            }
        }

    }

    //thêm mới  danh muc
    public static void createCatalog() {
        Catalog catalog = new Catalog();
        System.out.println("bạn hãy nhập tên danh mục");
        catalog.setCatalogName(InputMethods.getString());
        catalog.setCatalogId(catalogService.getNewId());
        catalogService.Save(catalog);
    }

    // hiển thị danh muc
    public static void showListCatalog() {
        List<Catalog> catalogs = catalogService.getAll();
        if (catalogs.size() == 0) {
            System.err.println("Danh sách danh mục  trống");
        } else {
            for (Catalog catalog : catalogs) {
                System.out.println(catalog);
            }
        }
    }

    // up date thông tin
    public static void editCatalog() {
        Catalog catalog = new Catalog();
        System.out.println("nhập vào id muốn sửa");
        int id = InputMethods.getInteger();
        if (catalogService.finById(id) == null) {
            System.err.println("không tìm thấy danh muc");

        } else {
            catalog = catalogService.finById(id);
            System.out.println("danh mục bạn cần sửa là");
            System.out.println(catalog);
            catalog.setCatalogId(id);
            System.out.println("nhập tên danh muc");
            catalog.setCatalogName(InputMethods.getString());
            System.out.println("bạn đã sửa  thành công");
            catalogService.Save(catalog);
        }

    }

    //xoá danh muc
    public static void deleteCatalog() {
        System.out.println("id danh mục bạn cần xoá là ");
        int idDel = InputMethods.getInteger();
        if (catalogService.deleteCatalog(idDel)) {
            System.out.println("Bạn đã xoá thành công");
        }
    }

    public static void menuProductManager() {
        byte choice = 0;
        while (choice != 7) {
            System.out.println(
                    "********************PRODUCT-MANAGEMENT********************\n" +
                            "1. Nhập số sản sản phẩm và nhập thông tin sản phẩm [5 điểm]\n" +
                            "2. Hiển thị thông tin các sản phẩm [5 điểm]\n" +
                            "3. Sắp xếp sản phẩm theo giá giảm dần [5 điểm]\n" +
                            "4. Xóa sản phẩm theo mã [5 điểm]\n" +
                            "5. Tìm kiếm sách theo tên sách [5 điểm]\n" +
                            "6. Thay đổi thông tin của sách theo mã sách [10 điểm]\n" +
                            "7. Quay lại  [10 điểm]");
            System.out.println("Nhập vào  lựa chọn của bạn");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // thêm mới sản phẩm
                    createNewProduct();
                    break;
                case 2:
                    // hiển thị
                    showListProduct();
                    break;
                case 3:
                    // sắp xếp sản phẩm
                    sortProduct();
                    break;
                case 4:
                    // Xóa sản phẩm theo mã
                    deleteProduct();
                    break;
                case 5:
                    // Tìm kiếm sách theo tên sách
                    //searchProduct();
                    break;
                case 6:
                    // Thay đổi thông tin của sách
                    editProductInfo();
                    break;
                case 7:
                    // quay lại
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 7");
                    break;
            }
        }

    }

    //thêm mới sản phẩm
    public static void createNewProduct() {
        List<Catalog> catalogs = catalogService.getAll();
        Product product = new Product();
        System.out.println("bạn hãy nhâp mã sản phẩm bắt đầu bằng chữ P và thêm 4 ký tự số ,");
        product.setProductId(InputMethods.getString());
        System.out.println("bạn hãy nhập tên sản phẩm");
        product.setProductName(InputMethods.getString());
        System.out.println("nhập giá");
        product.setProductPrice(InputMethods.getDouble());
        System.out.println("mô tả");
        product.setDescription(InputMethods.getString());
        System.out.println("cổ phần");
        product.setStock(InputMethods.getInteger());
        for (Catalog catalog : catalogs) {
            System.out.println(catalog);
        }
        System.out.println("mời bạn nhâp vào id danh muc muốn chọn");
        int id = InputMethods.getInteger();
        Catalog catalog = catalogService.finById(id);
        product.setCatalog(catalog);
        productService.Save(product);

    }

    // hiển thị danh sách san pham
    public static void showListProduct() {
        List<Product> products = productService.getAll();
        if (products.size() == 0) {
            System.err.println("Danh sách sản phẩm  trống");
        } else {
            for (Product product : products
            ) {

                System.out.println(product);
            }
        }
    }

    // up date thông tin
    public static void editProductInfo() {
        List<Catalog> catalogs = catalogService.getAll();
        Product newProduct = new Product();
        System.out.println("bạn hãy nhâp vào mã sản phẩm");
        String MSP = InputMethods.getProcutId();
        if (productService.finById(MSP) == null) {
            System.err.println("không tìm thấy sản phẩm cần sửa");

        } else {

            newProduct.setProductId(MSP);
            System.out.println("bạn hãy nhập tên sản phẩm");
            newProduct.setProductName(InputMethods.getString());
            for (Catalog catalog : catalogs) {
                System.out.println(catalog);
            }
            System.out.println("mời bạn nhâp vào id danh muc muốn chọn");
            int id = InputMethods.getInteger();
            Catalog catalog = catalogService.finById(id);
            newProduct.setCatalog(catalog);
            System.out.println("mô tả");
            newProduct.setDescription(InputMethods.getString());

            System.out.println("bạn đã sửa sản phẩm thành công");
            productService.Save(newProduct);
        }
    }

    //xoa sản phẩm
    public static void deleteProduct() {
        System.out.println("id sản phẩm cần xoá là");
        String idDel = InputMethods.getString();
        if (productService.deleteProduct(idDel)) {
            System.out.println("Bạn đã xoá sản phẩm thành công");
        } else {
            System.err.println("không tìm thấy sản phẩm để  xoá");
        }
    }

    //săp xếp sản phẩm the tên
    public static void sortProduct() {
        List<Product> products = productService.getAll();
        if (products.size() == 0) {
            System.err.println("Danh sách sản phẩm  trống bạn không thể sắp xếp");
            return;
        }
        products.sort((a, b) -> a.getProductName().compareTo(b.getProductName()));
        System.out.println("bạn đã sắp xếp thành công");
    }

    //người dùng quản lý
    public static void menuUserManager() {
        byte choice = 0;
        while (choice != 7) {
            System.out.println("" +
                    "**************************MENU-USER**************************\n" +
                    "1. Xem danh sách sản phẩm\n" +
                    "2. Thêm vào giỏ hàng\n" +
                    "3. Xem tất cả sản phẩm giỏ hàng\n" +
                    "4. Thay đổi số lượng sản phẩm trong giỏ hàng\n" +
                    "5. Xóa 1 sản phẩm trong giỏ hàng\n" +
                    "6. Xóa toàn bộ sản phẩm trong giỏ hàng\n" +
                    "7. Quay lại");
            System.out.println("Nhập vào  lựa chọn của bạn");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    //  Xem danh sách sản phẩm
                    viewProduct();
                    break;
                case 2:
                    // Thêm vào giỏ hàng
                    addCart();
                    break;
                case 3:
                    // Xem tất cả sản phẩm giỏ hàng
                    viewCart();
                    break;
                case 4:
                    // Thay đổi số lượng sản phẩm trong giỏ hàng
                    updateCart();
                    break;
                case 5:
                    // Xóa 1 sản phẩm trong giỏ hàng
                    deleteCart();
                    break;
                case 6:
                    // Xóa toàn bộ sản phẩm trong giỏ hàng
                    deleteAllCart();
                    break;
                case 7:
                    // quay lại
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 4");
                    break;
            }
        }
    }
    public static void viewProduct(){
        List<Product> products = productService.getAll();
        if (products.size() == 0) {
            System.err.println("Danh sách sản phẩm  trống");
        } else {
            for (Product product : products
            ) {
                System.out.println(product);
            }
        }
    }
    public static void addCart() {
        List<Product> products = productService.getAll();
         CartItem item = new CartItem();

        System.out.println("Nhập id sản phẩm cần thêm vào giỏ hàng: ");
        String id = InputMethods.getString();

        boolean productExists = false;

        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                productExists = true;
                item.setCartItemId(id);
                System.out.println("nhập tên sản phầm ");
                item.setProduct(InputMethods.getString());
                System.out.println("nhập số lượng");
                item.setQuantity(InputMethods.getInteger());
                System.out.println("nhập lại giá sản phẩm");
                item.setPrice(InputMethods.getDouble());
                cartService.Save(item);
                System.out.println("Bạn đã thêm thành công vào giỏ hàng.");
                break;
            }
        }

        if (!productExists) {
            System.err.println("Thêm vào giỏ hàng thất bại. Sản phẩm không tồn tại.");
        }
    }

    public static void viewCart(){
        List<CartItem> cartItems = cartService.getAll();
        if (cartItems.size() == 0) {
            System.err.println("Danh sách giỏ hàng trống ");
        } else {
            for (CartItem cartItem : cartItems
            ) {

                System.out.println(cartItem);
            }
        }
    }
    public static void updateCart() {
        CartItem cartItem = new CartItem();
        System.out.println("bạn hãy nhâp vào mã sản phẩm");
        String MSP = InputMethods.getProcutId();
        if (cartService.finById(MSP) == null) {
            System.err.println("không tìm thấy sản phẩm cần sửa");

        } else {
            cartItem.setCartItemId(MSP);
            System.out.println(" nhập tên sản phẩm");
            cartItem.setProduct(InputMethods.getString());
            System.out.println(" nhập số lượng ");
            cartItem.setQuantity(InputMethods.getInteger());
            System.out.println(" nhập giá");
            cartItem.setPrice(InputMethods.getDouble());
            System.out.println("bạn đã sửa sản phẩm thành công");
            cartService.Save(cartItem);
        }
    }
    public static void deleteCart(){
        System.out.println("id sản phẩm cần xoá là");
        String idDel = InputMethods.getString();
        if (cartService.deleteCart(idDel)) {
            System.out.println("Bạn đã xoá sản phẩm trong giỏ hàng thành công");
        } else {
            System.err.println("không tìm thấy sản phẩm để xoá");
        }
    }
    public static void deleteAllCart(){
        List<CartItem> cartItems = cartService.getAll();
        System.out.println("Đã xoá tất cả sản phẩm trong giỏ hàng  thành công");
        cartItems.clear();
    }
}
