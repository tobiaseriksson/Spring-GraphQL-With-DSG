package se.tsoft.spring.graphql.dsg.demo;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import se.tsoft.spring.graphql.dsg.demo.model.Offering;
import se.tsoft.spring.graphql.dsg.demo.model.Product;
import se.tsoft.spring.graphql.dsg.demo.model.ProductCategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class CatalogDataFetcher {

    private final List<Offering> offerings = Arrays.asList(
                    new Offering(300, 3000, "Rolex A1 Summer Sale", 100),
                    new Offering(301, 3400, "Rolex A1", 100));

    private final List<Product> products = Arrays.asList(
                    new Product(100, "Rolex A1", "Golden watch...", Arrays.asList(200), Arrays.asList(300,301)),
                    new Product(101, "Apple Watch 7", "Smart Watch from Apple...", Arrays.asList(201), new ArrayList<>()),
                    new Product(102, "Apple Watch 7 Gold", "Smart Watch from Apple...", Arrays.asList(201, 200),
                                    new ArrayList<>()),
                    new Product(103, "Samsung Watch s3", "Smart Watch from Samsung", Arrays.asList(202), new ArrayList<>()));

    private final List<ProductCategory> catagories = Arrays.asList(
                    new ProductCategory(200, "Expensive", Arrays.asList(100)),
                    new ProductCategory(201, "Apple", Arrays.asList(101, 102)),
                    new ProductCategory(202, "Samsung", Arrays.asList(103)));

    @DgsQuery
    public List<ProductCategory> categories(@InputArgument String nameFilter) {
        if (nameFilter == null) {
            return catagories;
        }
        return catagories.stream().filter(s -> s.getName().contains(nameFilter)).collect(Collectors.toList());
    }

    @DgsQuery
    public List<Product> products(@InputArgument String nameFilter) {
        if (nameFilter == null) {
            return products;
        }
        return products.stream().filter(s -> s.getName().contains(nameFilter)).collect(Collectors.toList());
    }

    @DgsData(parentType = "Product", field = "categories")
    public List<ProductCategory> productCategoriesForProduct(DgsDataFetchingEnvironment dfe) {

        Product product = dfe.getSource();
        List<ProductCategory> selection = catagories.stream().filter(f -> product.getCategories().contains(f.getId()))
                        .collect(Collectors.toList());
        return selection;
    }

    @DgsData(parentType = "ProductCategory", field = "products")
    public List<Product> productsForCategory(DgsDataFetchingEnvironment dfe) {

        ProductCategory category = dfe.getSource();
        List<Product> selection = products.stream().filter(f -> category.getProducts().contains(f.getId()))
                        .collect(Collectors.toList());
        return selection;
    }

    @DgsQuery
    public List<Offering> allOfferings() {
        return offerings;
    }

    @DgsData(parentType = "Offering", field = "products")
    public List<Product> productsForOffering(DgsDataFetchingEnvironment dfe) {

        Offering offering = dfe.getSource();
        List<Product> selection = products.stream().filter(f -> offering.getProduct() == f.getId())
                        .collect(Collectors.toList());
        return selection;
    }

    @DgsData(parentType = "Product", field = "offerings")
    public List<Offering> offeringsForProduct(DgsDataFetchingEnvironment dfe) {

        Product product = dfe.getSource();
        List<Offering> selection = offerings.stream().filter(f -> product.getOfferings().contains( f.getId() ))
                        .collect(Collectors.toList());
        return selection;
    }
}
